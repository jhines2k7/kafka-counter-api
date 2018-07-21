package com.jhinesconsulting.kafkacounterapi;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CounterEventRecordProducer {
    private Producer kafkaProducer;

    public CounterEventRecordProducer(Producer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void send(ProducerRecord producerRecord) {
        kafkaProducer.send(producerRecord, (RecordMetadata metadata, Exception exception) -> {
            if (exception == null) {
                System.out.println(metadata);
            } else {
                exception.printStackTrace();
            }
        });

//        kafkaProducer.flush();
//        kafkaProducer.close();
    }
}
