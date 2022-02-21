package fabric;

import java.util.ArrayList;

/**
 * <p>
 *     Вывод информации на экран и сохранение логов в список
 * </p>
 */
public class Display {

    private static ArrayList<String> logHistory = new ArrayList<>();

    public static void show(String s){
        logHistory.add(s);
        System.out.println(s);
    }

}
