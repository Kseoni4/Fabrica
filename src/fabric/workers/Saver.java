package fabric.workers;

import fabric.Storage;

import java.io.*;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *     Объект класса работает в отдельном потоке и необходим для периодической выгрузки <br>
 *
 *     (сохранения на диск\сериализации) содержимого {@link Storage} в файл production.pack
 * </p>
 */
public class Saver implements Runnable{

    private Storage linkedStorage;

    private ObjectOutputStream saveToFile;

    private FileOutputStream fileStream;

    public Saver(Storage linkedStorage){
        this.linkedStorage = linkedStorage;
    }

    @Override
    public void run() {
        try {
            File devices = new File("production.pack");

            fileStream = new FileOutputStream(devices);

            saveToFile = new ObjectOutputStream(fileStream);

            while (true) {
                saveProductionsIntoFile();
                waitFor();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProductionsIntoFile() throws IOException {
        saveToFile.writeObject(linkedStorage.getDevicesContainer());
    }

    private void waitFor(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
