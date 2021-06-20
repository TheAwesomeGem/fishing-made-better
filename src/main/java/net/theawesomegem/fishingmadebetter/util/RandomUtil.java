package net.theawesomegem.fishingmadebetter.util;

import java.util.Random;

/**
 * Created by TheAwesomeGem on 12/27/2017.
 */
public class RandomUtil {
    public static int getRandomInRange(Random random, int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
