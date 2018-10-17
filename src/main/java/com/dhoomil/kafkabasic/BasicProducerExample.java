package com.dhoomil.kafkabasic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

public class BasicProducerExample {
    public static void main(String[] args) throws InterruptedException {
        // We first define the configuration for Producer
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        BasicProducerCallback callback = new BasicProducerCallback();

        System.out.println("Sending 1 message every 1 second, for a total of 100 messages:");
        for (int i = 0; i < 100; i++) {
            String message = "Message Number: " + i + " at " + new Date();
            String key = "key" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>("test", key, message);
            producer.send(record, callback);
            Thread.sleep(1000L);
        }
        producer.close();
    }
}
