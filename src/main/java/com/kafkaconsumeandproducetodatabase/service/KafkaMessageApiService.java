package com.kafkaconsumeandproducetodatabase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaconsumeandproducetodatabase.configs.KafkaProducerConfigs;
import com.kafkaconsumeandproducetodatabase.kafka.MessageProducer;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class KafkaMessageApiService {
    private MessageProducer messageProducer;

    public void postMessageToKafkaTopic(Message message) throws JsonProcessingException {

        messageProducer.send(message);



    }

    public String getMessageFromElasticSearchViaUID(SearchWithUID searchWithUID) {

        String message = new RestTemplate().getForObject("http://localhost:8081/GetMessageByUID?message={message}", String.class, searchWithUID.getMessage());
        System.out.println("message recieved = " + message);
        System.out.println("----------------------------------");
    return message;
    }

}
