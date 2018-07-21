package com.jhinesconsulting.kafkacounterapi;

public class Counter {
    private int count;

    int getCount() {
        return count;
    }

    void incrementCount(int amount) {
        this.count += amount;
    }

    void decrementCount(int amount) {
        this.count -= amount;
    }

    void resetCount() {
        this.count = 0;
    }
}
