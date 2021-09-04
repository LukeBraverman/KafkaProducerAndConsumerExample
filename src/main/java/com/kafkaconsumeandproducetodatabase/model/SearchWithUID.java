package com.kafkaconsumeandproducetodatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchWithUID {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
