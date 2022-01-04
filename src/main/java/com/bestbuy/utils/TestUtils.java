package com.bestbuy.utils;

import java.util.Random;

/**
 * By Dimple Patel
 **/
public class TestUtils
{
    //toString return a String object which represents the mentioned integer in the parameter.
    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }
}
