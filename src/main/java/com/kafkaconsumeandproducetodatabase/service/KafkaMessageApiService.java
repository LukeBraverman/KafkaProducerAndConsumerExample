package com.kafkaconsumeandproducetodatabase.service;

import com.kafkaconsumeandproducetodatabase.configs.KafkaProducerConfigs;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class KafkaMessageApiService {
    private KafkaProducerConfigs kafkaProducerConfigs;

    public void postMessageToKafkaTopic(Message message) {


    }

    public Message getMessageFromElasticSearchViaUID(SearchWithUID searchWithUID) {

    return new Message();
    }

}
