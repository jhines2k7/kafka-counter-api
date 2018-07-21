package com.jhinesconsulting.kafkacounterapi;

import com.jhinesconsulting.CounterEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

@Component
public class CounterEventRecordConsumer {
    private KafkaConsumer kafkaConsumer;
    private CounterEventRecordStore counterEventRecordStore;

    public CounterEventRecordConsumer(KafkaConsumer kafkaConsumer, CounterEventRecordStore counterEventRecordStore) {
        this.kafkaConsumer = kafkaConsumer;
        this.counterEventRecordStore = counterEventRecordStore;
    }

    public void poll() {
        while (true) {
            System.out.println("Polling");

            ConsumerRecords<String, CounterEvent> records = kafkaConsumer.poll(1000);

            for (ConsumerRecord<String, CounterEvent> record : records) {
                CounterEvent counterEvent = record.value();
                System.out.println(record.partition() + "@" + record.offset() + " " + counterEvent);

                counterEventRecordStore.getCounterEvents().add(counterEvent);
            }

            kafkaConsumer.commitSync();
        }
    }
}
