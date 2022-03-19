package fabric;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Линия сборки для большого изделия
 * @see MegaDevice
 */
public class BigProcessingLine implements Runnable{

    private ExecutorService workers = Executors.newCachedThreadPool();

    private Storage fabricStorage;

    /**
     * Запускает процесс создания МегаИзделий {@link MegaDevice}
     * @throws InterruptedException
     */
    private void startWorking() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            createMegaDevice();
            TimeUnit.SECONDS.sleep(3);
        }
        fabricStorage.getDeviceContainers().size();
    }


    private void createMegaDevice(){
        workers.execute(()-> {
            try {
                Display.show("Start creating mega device...");

                TimeUnit.SECONDS.sleep(2);

                Container container = new Container(new MegaDevice());

                Display.show("Device "+container.getDevice().getName()+" has created and put into container");

                Display.show("Container put into storage");

                fabricStorage.putContainerInto(container);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void run() {
        try {
            startWorking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BigProcessingLine(Storage fabricStorage){
        this.fabricStorage = fabricStorage;
    }
}
