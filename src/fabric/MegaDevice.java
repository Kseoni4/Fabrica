package fabric;

import fabric.Display;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * //В РАЗРАБОТКЕ\\
 * Здесь будет класс "большого изделия", занимающее приличное место в памяти.
 * Изделие будет штучным.
 */
public class MegaDevice {

    private static String[] deviceNameA = {"QTS","CPD","MSP","SBW","GIS","WAP","VJS","PLJ","MPM","HKS"};

    private static String[] deviceNameB = {"293","101","563","901","560","234","643","103","650","590"};

    private static String[] deviceNameC = {"A","S","C","D","Q","OMEGA","ALPHA","[CLASSIFIED]"};

    public long[] someLongNumbers;

    public String name;

    public MegaDevice(){
        this.name = makeName();
        fillNumbers();
    }

    private String makeName(){
        int randomIndexA = ThreadLocalRandom.current().nextInt(deviceNameA.length);
        int randomIndexB = ThreadLocalRandom.current().nextInt(deviceNameB.length);
        int randomIndexC = ThreadLocalRandom.current().nextInt(deviceNameC.length);
        return deviceNameA[randomIndexA]+"-"+deviceNameB[randomIndexB]+"|TYPE:"+deviceNameC[randomIndexC];
    }

    private void fillNumbers(){
        int maxBound = Integer.MAX_VALUE/8;

        Display.show("MaxBound: "+maxBound);

        int randomSize = ThreadLocalRandom.current().nextInt(10, maxBound);

        Display.show("Size of long numbers "+randomSize);

        someLongNumbers = new long[randomSize];

        int susBond = ThreadLocalRandom.current().nextInt(0,3);

        for(int i = 0; i < randomSize+susBond; i++){
            someLongNumbers[i] = ThreadLocalRandom.current().nextLong();
        }
    }

    public long[] getSomeLongNumbers() {
        return someLongNumbers;
    }

    public String getName() {
        return name;
    }

    @Override
    public void finalize(){
        return;
    }
}
