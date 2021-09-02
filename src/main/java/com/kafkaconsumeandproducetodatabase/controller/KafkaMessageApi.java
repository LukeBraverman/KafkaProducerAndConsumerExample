package com.kafkaconsumeandproducetodatabase.controller;

import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/KMApi")
public class KafkaMessageApi {


    @PostMapping("/Post")
    public void postMessageToElasticSearch(@RequestBody Message message) {


    }

    @GetMapping(value = "/Get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMessageFromElasticSearchViaUID(@RequestBody SearchWithUID searchWithUID) {


        return "";
    }
}
