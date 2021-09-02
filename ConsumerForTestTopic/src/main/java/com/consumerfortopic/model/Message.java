package com.consumerfortopic.model;

import lombok.Data;


@Data
public class Message {

     String message;
     String anId;
     String topic;

    public String getMessage() {
        return message;
    }

    public String getAnId() {
        return anId;
    }

    public String getTopic() {
        return topic;
    }


}
