package fabric;

import fabric.workers.Maker;
import fabric.workers.Saver;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
     * Основной класс для работы "фабричной линии"<br>
     * Последовательностьосновного цикла выполнения:<br>
     * <li>
     *         Запустить "рабочего" (как отдельный поток), который поставит на склад ({@link Storage}) N объектов класса {@link Device} <br>
     * </li>
 * <li>
     * Подождать 30 миллисекунд.<br>
 * </li>
 * <li>
     *  Проверить устройства, вызвав их внутренний метод {@code .getSomeNumber()};<br>
 *  </li>
     * Цикл повторяется до тех пор, пока на складе не будет определённое количество девайсов.
 * </p>
 */
public class ProcessingLine implements Runnable{

    private ExecutorService workers = Executors.newCachedThreadPool();

    private Storage fabricStorage;

    public int requiredQuantity;

    /**
     * Запускает процесс производства изделий {@link Device}
     * @param requiredQuantity количество изделий, которое необходимо произвести каждому рабочему потоку
     */
    private void startWorking(int requiredQuantity){
        workers.execute(new Saver(fabricStorage));

        while(fabricStorage.getDevicesContainer().size() < 300_000){

           addWorker(requiredQuantity);

           waitFor();

           checkDevices();
        }

        workers.shutdown();
    }

    /**
     * Выводит на экран текущее содержимое склада произведённых изделий в формате:<br>
     * {@code Название_изделия - случайный номер из массива int[]}
     */
    private void checkDevices(){
        ArrayList<Device> currentDevices = new ArrayList<>(fabricStorage.getDevicesContainer());
        for(Device d : currentDevices){
            Display.show(d.getName() + " " +d.getSomeNumber());
        }
    }

    /**
     * Добавляет нового рабочего в пул потоков {@code workers}
     * @param requiredQuantity количество изделий, которое необходимо произвести рабочему
     */
    private void addWorker(int requiredQuantity) {
        Maker worker = new Maker(fabricStorage, requiredQuantity);
        workers.execute(worker);
    }

    /**
     * Останавливает выполнение на некоторое количество миллисекунд
     */
    private void waitFor(){
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        startWorking(requiredQuantity);
    }

    public ProcessingLine(int requiredQuantity, Storage fabricStorage){
        this.requiredQuantity = requiredQuantity;
        this.fabricStorage = fabricStorage;
    }
}
