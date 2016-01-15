package com.github.sebtom.goeuro.utils;

public class Timer {

    private long startTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public double getElapsedTime() {
        long endTime = System.currentTimeMillis();
        return (double) (endTime - startTime) / (1000);
    }
}
