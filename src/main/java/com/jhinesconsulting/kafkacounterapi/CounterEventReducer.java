package com.jhinesconsulting.kafkacounterapi;

import com.jhinesconsulting.CounterEvent;

import java.util.List;

public class CounterEventReducer {
    public static Counter reduce(List<CounterEvent> counterEvents) {
        Counter counter = new Counter();

        for (CounterEvent counterEvent : counterEvents) {
            if (counterEvent.getAction().equals("INCREMENT")) {
                counter.incrementCount(counterEvent.getAmount());
            } else if(counterEvent.getAction().equals("RESET")) {
                counter.resetCount();
            } else {
                counter.decrementCount(counterEvent.getAmount());
            }
        }

        return counter;
    }
}
