package fabric.workers;

import fabric.Device;
import fabric.Display;
import fabric.Storage;

import java.util.concurrent.TimeUnit;

/**
 * <b>Временно не используется (?)</b><br>
 * Класс описывающий деятельность некого рабочего, производящий определённое количество изделий ({@link Device})<br>
 * Объекты класса создаются и запускаются в отдельном потоке
 */
public class Maker implements Runnable{

    Storage linkedStorage;

    int requiredQuantity;

    public Maker(Storage linkedStorage, int requiredQuantity){
        this.linkedStorage = linkedStorage;

        this.requiredQuantity = requiredQuantity;
    }

    @Override
    public void run() {
        while(true){
            for(int i = 0; i < requiredQuantity; i++) {
                produce();
                waitForMilliSeconds(100);
            }
            waitForSeconds(3);
        }
    }

    private void produce(){
        Device newDevice = new Device();
        Display.show("Worker"+Thread.currentThread().getName()+" make device "+newDevice.getName());
        linkedStorage.putDeviceInto(newDevice);
    }

    private void waitForMilliSeconds(int number){
        try {
            TimeUnit.MILLISECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForSeconds(int number){
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
