package com.kafkaconsumeandproducetodatabase.kafka;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer extends BaseMessageProducer {

    public MessageProducer(
            @Qualifier("KafkaMessageSender")
            KafkaTemplate<String, String> kafkaTemplate)
    {
        super(kafkaTemplate);
    }
}
