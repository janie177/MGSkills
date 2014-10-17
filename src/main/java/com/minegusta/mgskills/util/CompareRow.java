package com.minegusta.mgskills.util;

import java.util.Arrays;
import java.util.Comparator;

public class CompareRow
{
    public static int[][] order(int[][] i)
    {
        Arrays.sort(i, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        return i;
    }
}
