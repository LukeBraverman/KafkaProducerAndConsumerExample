package com.consumerfortopic.consumer;

import com.consumerfortopic.model.Message;
import com.consumerfortopic.service.KafkaConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class KafkaListener {

    private KafkaConsumerService kafkaConsumerService;

    @org.springframework.kafka.annotation.KafkaListener(topics = "ES_TEST_TOPIC", groupId = "ESTEST")
    public void ListenerOnTopicES_TEST_TOPIC(String message) {
        System.out.println("received message: " + message);
        try {
            Message messageReceived = kafkaConsumerService.convertStringToMessageJavaObject(message);
            System.out.println(messageReceived.toString());
            kafkaConsumerService.addMessageFromKafkaToPostgres(messageReceived);
        } catch (JsonProcessingException e) {
            System.out.println("Not A JSON string");
            e.printStackTrace();
        }


    }
}
