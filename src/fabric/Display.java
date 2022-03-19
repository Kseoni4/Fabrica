package fabric;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *     Вывод информации на экран и сохранение логов в список
 * </p>
 */
public class Display {

    /**
     * Хранилище текстовых логов, которое генерирует приложение
     * !!!Сильно забивает память и не очищается!!!
     */
    private static ArrayList<String> logHistory = new ArrayList<>();

    private static SoftReference<ArrayList<String>> listSoftReference = new SoftReference<>(logHistory);

    private static Logger logger = Logger.getGlobal();

    public static void show(String s) {
        listSoftReference.get().add(s);
        logger.info(s);
    }

    private Display(){}
}
