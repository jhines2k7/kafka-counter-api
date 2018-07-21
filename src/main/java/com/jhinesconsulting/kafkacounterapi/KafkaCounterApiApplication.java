package com.jhinesconsulting.kafkacounterapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaCounterApiApplication implements CommandLineRunner {
    @Autowired
    private CounterEventRecordConsumer counterEventRecordConsumer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaCounterApiApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        counterEventRecordConsumer.poll();
    }
}
