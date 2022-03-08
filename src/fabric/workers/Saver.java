package fabric.workers;

import fabric.Device;
import fabric.Storage;

import java.io.*;
import java.util.ArrayList;
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
            openStreams();

            while (true) {
                saveProductionsIntoFile();
                closeStreams();
                waitFor();
                openStreams();
            }

        } catch (IOException | InterruptedException e) {
            return;
        }
    }

    private void openStreams() throws IOException {
        File devices = new File("production.pack");

        fileStream = new FileOutputStream(devices);

        saveToFile = new ObjectOutputStream(fileStream);
    }

    private void closeStreams() throws IOException {
        fileStream.close();
        saveToFile.close();
    }

    private void saveProductionsIntoFile() throws IOException {
        ArrayList<Device> bufferStorage = new ArrayList<>(linkedStorage.getDevicesContainer());
        saveToFile.writeObject(bufferStorage);
    }

    private void waitFor() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }
}
