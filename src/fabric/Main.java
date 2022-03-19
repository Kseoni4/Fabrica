package fabric;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс для точки входа в программу
 */
public class Main {

    private static ExecutorService fabricLines = Executors.newCachedThreadPool();

    private static final int DEFAULT_QUANTITY = 1000;

    private static Storage fabricStorage = new Storage();

    /**
     * Точка входа в программу, метод создаёт новый объект класса {@link ProcessingLine} для последующего запуска
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {

        int requiredQuantity = DEFAULT_QUANTITY;

        if(args.length >= 1){
            requiredQuantity = Integer.parseInt(args[0]);
        }

        ProcessingLine processingLine = new ProcessingLine(requiredQuantity, fabricStorage);

        BigProcessingLine bigProcessingLine = new BigProcessingLine(fabricStorage);

        fabricLines.execute(processingLine);

        fabricLines.execute(bigProcessingLine);

        //fabricLines.shutdown();
    }


}
