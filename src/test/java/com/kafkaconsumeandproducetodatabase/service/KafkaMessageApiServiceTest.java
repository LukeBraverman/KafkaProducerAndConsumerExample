package com.kafkaconsumeandproducetodatabase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaconsumeandproducetodatabase.kafka.MessageProducer;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
//@RestClientTest(KafkaMessageApiService.class)
class KafkaMessageApiServiceTest {
    @InjectMocks
    KafkaMessageApiService kafkaMessageApiService;

    @Mock
    private MessageProducer messageProducer;


    @Mock
    private RestTemplate restTemplate;



    @Test
    public void whenRecieveMessage_ThenCallMessageProducer() throws JsonProcessingException {
        //given
        doNothing().when(messageProducer).send(any(Message.class));

        //when
        kafkaMessageApiService.postMessageToKafkaTopic(new Message());

        //then
        verify(messageProducer, times(1)).send(any(Message.class));
    }

    @Test
    public void whenRestTemplateRequestSentToConsumerAPI_ReturnStringAndSucess() {
        //given

        SearchWithUID searchWithUID = new SearchWithUID();
        searchWithUID.setMessage("A-UNIQUE-UID");
        Mockito
                .when(restTemplate.getForObject( "http://localhost:8081/GetMessageByUID?message={message}" , String.class, searchWithUID.getMessage()))
          .thenReturn(String.valueOf(new ResponseEntity("messageInDatabase", HttpStatus.OK)));
        //when
        kafkaMessageApiService.getMessageFromPostgresViaUID(searchWithUID);

        //then
        verify(restTemplate, times(1)).getForObject( "http://localhost:8081/GetMessageByUID?message={message}" , String.class, searchWithUID.getMessage());
    }



}