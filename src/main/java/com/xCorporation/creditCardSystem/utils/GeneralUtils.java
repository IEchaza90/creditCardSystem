package com.xCorporation.creditCardSystem.utils;

import java.util.Random;

public class GeneralUtils {

    public static Integer generateRandomCvv() {
        Random r = new Random();
        int low = 100;
        int high = 1000;
        return r.nextInt(high-low) + low;
    }
}
