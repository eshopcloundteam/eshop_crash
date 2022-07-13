package com.example.eshop_crash;

public class CrashMaker {
    public void javaCException() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("make a runtime exception from java");
            }
        }).start();
    }

}
