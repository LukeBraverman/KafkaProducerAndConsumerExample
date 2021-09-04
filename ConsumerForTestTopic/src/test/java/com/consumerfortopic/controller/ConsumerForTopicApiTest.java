package com.consumerfortopic.controller;

import com.consumerfortopic.service.KafkaConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)


class ConsumerForTopicApiTest {

    private MockMvc mockMvc;
    @InjectMocks
    ConsumerForTopicApi consumerForTopicApi;
    @Mock
    KafkaConsumerService kafkaConsumerService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consumerForTopicApi)
                .build();
    }

    @Test
    void GETMessageFORMDatabase_returnMessageInDatabase() throws Exception {
        //given



        when(kafkaConsumerService.getMessageFromDatabase_thenConvertToJSON("A-UID")).thenReturn("ReturnedMessage");

        //when
        final MvcResult mvcResult = mockMvc.perform(
                get("/GetMessageByUID")
                        .param("message", "A-UID"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        //then

        verify(kafkaConsumerService,times(1)).getMessageFromDatabase_thenConvertToJSON("A-UID");
        assertEquals("ReturnedMessage", content);
    }



}