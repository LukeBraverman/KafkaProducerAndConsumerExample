package com.kafkaconsumeandproducetodatabase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import com.kafkaconsumeandproducetodatabase.service.KafkaMessageApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/KMApi")
public class KafkaMessageApi {
    private KafkaMessageApiService kafkaMessageApiService;

    @PostMapping("/Post")
    public Object postMessageToElasticSearch(@RequestBody Message message) throws JsonProcessingException {
        String randomUID = UUID.randomUUID().toString();
        message.setUID(randomUID);
        kafkaMessageApiService.postMessageToKafkaTopic(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Posted message to database with uid " + randomUID);
    }

    @GetMapping(value = "/Get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMessageFromElasticSearchViaUID(@RequestBody SearchWithUID searchWithUID) throws JsonProcessingException {
        System.out.println("Pinged");
        String jsonMessage = kafkaMessageApiService.getMessageFromElasticSearchViaUID(searchWithUID);



        return jsonMessage;
    }
}
