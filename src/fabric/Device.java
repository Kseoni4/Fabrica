package fabric;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *  Класс представляющий собой описание некого "устройства" или изделия, которое производит фабрика<br>
 *  Единственная функция на данный момент {@code .getSomeNumber()} - возвращает случайное число из предварительно заполненного массива int[]
 * </p>
 */
public class Device {

    private static final String[] deviceNameA = {"BVX","SAO","ITM","DDO","VXZ","WOD","ZTB","PVI"};

    private static final String[] deviceNameB = {"20","24","01","07","12","09","44","91"};

    private final int[] someNumbers;

    private final String name;

    public Device(){
        name = makeName();
        someNumbers = insertSomeNumbers();
    }

    /**
     * Соединяет две случайных строки из массивов A и B, получая название изделия
     * @return полученное имя в формате XXX-NN;
     */
    private String makeName(){
       int randomA = ThreadLocalRandom.current().nextInt(deviceNameA.length);

       int randomB = ThreadLocalRandom.current().nextInt(deviceNameB.length);

       return deviceNameA[randomA] + "-" + deviceNameB[randomB];
    }

    /**
     * Инициализирует и заполняет массив int[] someNumbers случайными числами от 1 до {@code Integer.MAX_VALUE}
     * @return полученный массив случайных чисел
     */
    private int[] insertSomeNumbers(){
       int[] buff = new int[ThreadLocalRandom.current().nextInt(5,100)];

       for(int i = 0; i < buff.length;i++){
           buff[i] = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
       }

       return buff;
    }

    public String getName() {
        return name;
    }

    public int getSomeNumber(){
        int randomIndex = ThreadLocalRandom.current().nextInt(someNumbers.length);
        return someNumbers[randomIndex];
    }
}
