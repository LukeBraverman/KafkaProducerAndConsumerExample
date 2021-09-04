package com.kafkaconsumeandproducetodatabase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaconsumeandproducetodatabase.kafka.MessageProducer;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class KafkaMessageApiService {
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private RestTemplate restTemplate;

    public void postMessageToKafkaTopic(Message message) throws JsonProcessingException {

        messageProducer.send(message);



    }

    public String getMessageFromPostgresViaUID(SearchWithUID searchWithUID) {

        String message = restTemplate.getForObject("http://localhost:8081/GetMessageByUID?message={message}", String.class, searchWithUID.getMessage());
    return message;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
