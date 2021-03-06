package com.minegusta.mgskills.util;

import java.util.Random;

public class RandomNumber {
    private static final Random rand = new Random();

    /**
     * @param max The maximum number to get.
     * @return Number is always 1-50 including those two.
     */
    public static int get(int max) {
        return rand.nextInt(max) + 1;
    }



    public static boolean getBoolean()
    {
        return get(2) == 1;
    }
}
