package com.bbh.compugain.search.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by smutyala on 5/6/2017.
 */
public class BBHProducer {
    private static Producer<String, String> producer;
    private final Properties properties = new Properties();
    private static final String KAFKA_SERVER = ConfigFactory.load().getString("lagom.broker.kafka.brokers");
    private static final String MESSAGE_QUERIES_DETAILS_TOPIC_NAME = ConfigFactory.load().getString("lagom.broker.kafka.topic");

    public BBHProducer() {
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("request.required.acks", "1");
        producer = new KafkaProducer<String, String>(properties);
    }
    public boolean sendMessage(Object bbh540Message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(bbh540Message);
            ProducerRecord<String, String> rc = new ProducerRecord<String, String>(MESSAGE_QUERIES_DETAILS_TOPIC_NAME, json);
            producer.send(rc);
            producer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
