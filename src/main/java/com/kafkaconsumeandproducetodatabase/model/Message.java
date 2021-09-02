package com.kafkaconsumeandproducetodatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Message {

     String message;
     String UID;
     String topic;

    public String getMessage() {
        return message;
    }

    public String getUID() {
        return UID;
    }

    public String getTopic() {
        return topic;
    }
}
