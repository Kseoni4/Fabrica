package fabric.underconstruction;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * //В РАЗРАБОТКЕ\\
 * Здесь будет класс "большого изделия", занимающее приличное место в памяти.
 * Изделие будет штучным.
 */
public class MegaDevice {

    private static String[] deviceNameA = {};

    private static String[] deviceNameB = {};

    private static String[] deviceNameC = {};

    private long[] someLongNumbers;

    private String name;

    public MegaDevice(){
        this.name = makeName();
    }

    private String makeName(){
        int randomIndexA = ThreadLocalRandom.current().nextInt(deviceNameA.length);
        int randomIndexB = ThreadLocalRandom.current().nextInt(deviceNameB.length);
        int randomIndexC = ThreadLocalRandom.current().nextInt(deviceNameC.length);
        return deviceNameA[randomIndexA]+"-"+deviceNameB[randomIndexB]+"|TYPE:"+deviceNameC[randomIndexC];
    }

    public long[] getSomeLongNumbers() {
        return someLongNumbers;
    }

    public String getName() {
        return name;
    }
}
