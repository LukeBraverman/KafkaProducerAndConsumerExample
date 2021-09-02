package com.kafkaconsumeandproducetodatabase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaconsumeandproducetodatabase.configs.KafkaProducerConfigs;
import com.kafkaconsumeandproducetodatabase.kafka.MessageProducer;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class KafkaMessageApiService {
    private MessageProducer messageProducer;

    public void postMessageToKafkaTopic(Message message) throws JsonProcessingException {

        messageProducer.send(message);



    }

    public Message getMessageFromElasticSearchViaUID(SearchWithUID searchWithUID) {

    return new Message();
    }

}
