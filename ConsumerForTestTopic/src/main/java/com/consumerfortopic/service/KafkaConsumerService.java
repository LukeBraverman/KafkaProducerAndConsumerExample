package com.consumerfortopic.service;

import com.consumerfortopic.model.Message;
import com.consumerfortopic.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private MessageRepository messageRepository;


    public void addMessageFromKafkaToPostgres(Message message) {
        messageRepository.save(message);
    }

    public Message convertStringToMessageJavaObject(String messageInJson) throws JsonProcessingException {
        messageInJson.trim();
        ObjectMapper mapper = new ObjectMapper();
        Message messageReceived = mapper.readValue(messageInJson, Message.class);
        return messageReceived;
    }
}
