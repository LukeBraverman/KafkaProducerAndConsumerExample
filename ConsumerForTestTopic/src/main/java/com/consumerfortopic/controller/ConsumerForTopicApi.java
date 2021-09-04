package com.consumerfortopic.controller;

import com.consumerfortopic.service.KafkaConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConsumerForTopicApi {

    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/GetMessageByUID" )
    public String getMessageInDatabaseViaUID(@RequestParam String message) {

        String messageFormDb = kafkaConsumerService.getMessageFromDatabase_thenConvertToJSON(message);
        return messageFormDb;

    }


}
