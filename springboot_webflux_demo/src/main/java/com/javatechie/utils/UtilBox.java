package com.javatechie.utils;

import java.util.concurrent.TimeUnit;

public class UtilBox {

    public static void delay( final int delayInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
