package com.jhinesconsulting.kafkacounterapi;

import com.jhinesconsulting.CounterEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/counter")
public class CounterController {
    @Autowired
    private CounterEventRecordStore counterEventRecordStore;

    @Autowired
    private CounterEventRecordProducer counterEventRecordProducer;

    CounterEvent.Builder counterEventBuilder;

    @Autowired
    ApplicationProperties applicationProperties;

    @GetMapping("/total")
    public int getTotal() {
        return CounterEventReducer.reduce(counterEventRecordStore.getCounterEvents()).getCount();
    }

    @PostMapping("/increment")
    ResponseEntity<?> increment(@RequestBody Map<String, Integer> payload) {
        counterEventBuilder = CounterEvent.newBuilder();
        counterEventBuilder.setAction("INCREMENT");
        counterEventBuilder.setAmount(payload.get("amount"));

        CounterEvent counterEvent = counterEventBuilder.build();

        counterEventRecordProducer.send(
                new ProducerRecord<String, CounterEvent>(applicationProperties.getTopic(), counterEvent)
        );

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/decrement")
    ResponseEntity<?> decrement(@RequestBody Map<String, Integer> payload) {
        counterEventBuilder = CounterEvent.newBuilder();
        counterEventBuilder.setAction("DECREMENT");
        counterEventBuilder.setAmount(payload.get("amount"));

        CounterEvent counterEvent = counterEventBuilder.build();

        counterEventRecordProducer.send(new ProducerRecord<String, CounterEvent>(applicationProperties.getTopic(), counterEvent));

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset")
    ResponseEntity<?> reset(@RequestBody Map<String, Integer> payload) {
        counterEventBuilder = CounterEvent.newBuilder();
        counterEventBuilder.setAction("RESET");
        counterEventBuilder.setAmount(0);

        CounterEvent counterEvent = counterEventBuilder.build();

        counterEventRecordProducer.send(new ProducerRecord<String, CounterEvent>(applicationProperties.getTopic(), counterEvent));

        return ResponseEntity.noContent().build();
    }
}
