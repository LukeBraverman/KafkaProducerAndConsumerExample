package com.kafkaconsumeandproducetodatabase.controller;

import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import com.kafkaconsumeandproducetodatabase.service.KafkaMessageApiService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/KMApi")
public class KafkaMessageApi {
    private KafkaMessageApiService kafkaMessageApiService;

    @PostMapping("/Post")
    public void postMessageToElasticSearch(@RequestBody Message message) {
        kafkaMessageApiService.postMessageToKafkaTopic(message);

    }

    @GetMapping(value = "/Get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMessageFromElasticSearchViaUID(@RequestBody SearchWithUID searchWithUID) {
        Message message = kafkaMessageApiService.getMessageFromElasticSearchViaUID(searchWithUID);

        String jsonMessage = "";
        return jsonMessage;
    }
}
