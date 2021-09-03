package com.consumerfortopic.model;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Message {

     @Id
     String uid;
     String topic;
     String message;

    public String getMessage() {
        return message;
    }

    public String getUid() {
        return uid;
    }

    public String getTopic() {
        return topic;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUid(String anId) {
        this.uid = anId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
