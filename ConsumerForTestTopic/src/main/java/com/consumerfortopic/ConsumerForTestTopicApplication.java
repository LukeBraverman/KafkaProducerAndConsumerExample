package com.consumerfortopic;



import com.consumerfortopic.repository.MessageRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;


@SpringBootApplication
public class ConsumerForTestTopicApplication {



    public static void main(String[] args) throws IOException {
        SpringApplication.run(ConsumerForTestTopicApplication.class, args);
    }}
