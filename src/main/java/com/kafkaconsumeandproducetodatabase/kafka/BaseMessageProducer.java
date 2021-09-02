package com.kafkaconsumeandproducetodatabase.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kafkaconsumeandproducetodatabase.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;


public abstract class BaseMessageProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;



    public BaseMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;

    }


    public void send(final Message message) throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(message);

        org.springframework.messaging.Message<String> stringMessage = MessageBuilder
                .withPayload(json)
                .setHeader(KafkaHeaders.TOPIC, message.getTopic())
                .build();
        kafkaTemplate.send(stringMessage);


    }
}
