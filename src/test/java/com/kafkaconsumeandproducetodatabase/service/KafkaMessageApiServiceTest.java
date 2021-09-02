package com.kafkaconsumeandproducetodatabase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaconsumeandproducetodatabase.kafka.MessageProducer;
import com.kafkaconsumeandproducetodatabase.model.Message;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest

class KafkaMessageApiServiceTest {
    @InjectMocks
    KafkaMessageApiService kafkaMessageApiService;

    @Mock
    private MessageProducer messageProducer;


    @Test
    public void whenRecieveMessage_ThenCallMessageProducer() throws JsonProcessingException {
        //given
        doNothing().when(messageProducer).send(any(Message.class));

        //when
        kafkaMessageApiService.postMessageToKafkaTopic(new Message());

        //then
        verify(messageProducer, times(1)).send(any(Message.class));
    }

}