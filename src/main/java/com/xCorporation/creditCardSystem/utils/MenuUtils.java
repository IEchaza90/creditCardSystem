package com.xCorporation.creditCardSystem.utils;

import java.util.Date;
import java.util.Random;

public class MenuUtils {

    public static Integer generateRandomCvv() {
        Random r = new Random();
        int low = 100;
        int high = 1000;
        return r.nextInt(high-low) + low;
    }
}
