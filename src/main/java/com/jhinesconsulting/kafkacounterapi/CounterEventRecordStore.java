package com.jhinesconsulting.kafkacounterapi;

import com.jhinesconsulting.CounterEvent;

import java.util.ArrayList;
import java.util.List;

public class CounterEventRecordStore {
    private List<CounterEvent> counterEvents = new ArrayList<>();

    public List<CounterEvent> getCounterEvents() {
        return this.counterEvents;
    }
}
