package com.minegusta.mgskills.util;

import java.util.Random;

public class RandomNumber {
    private static Random rand = new Random();

    public static int get(int max) {

        return rand.nextInt(max) + 1;
    }
}
