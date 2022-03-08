package fabric;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

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

    public static void show(String s) {
        //logHistory.add(s);
        System.out.println(s);
    }

}
