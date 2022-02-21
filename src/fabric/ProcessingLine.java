package fabric;

import fabric.workers.Maker;
import fabric.workers.Saver;

import java.lang.ref.WeakReference;
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
 *
 *
 *
 */
public class ProcessingLine {

    private final ExecutorService workers = Executors.newCachedThreadPool();

    private Storage fabricStorage;

    /**
     * Запускает процесс производства изделий {@link Device}
     * @param requiredQuantity количество изделий, которое необходимо произвести каждому рабочему потоку
     */
    public void startWorking(int requiredQuantity){
        fabricStorage = new Storage();

        workers.execute(new Saver(fabricStorage));


        while(fabricStorage.getDevicesContainer().size() < 500_000){

           addWorker(requiredQuantity);

           waitFor();

           checkDevices();
        }
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
    private void addWorker(int requiredQuantity){
        workers.execute(()-> {
            for (int i = 0; i < requiredQuantity; i++) {
                fabricStorage.putDeviceInto(new Device());
            }
        });
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
}
