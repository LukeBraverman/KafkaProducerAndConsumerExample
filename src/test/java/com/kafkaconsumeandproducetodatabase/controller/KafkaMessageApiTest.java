package com.kafkaconsumeandproducetodatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kafkaconsumeandproducetodatabase.model.Message;
import com.kafkaconsumeandproducetodatabase.model.SearchWithUID;
import com.kafkaconsumeandproducetodatabase.service.KafkaMessageApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)

class KafkaMessageApiTest {
    private MockMvc mockMvc;
    @InjectMocks
    KafkaMessageApi kafkaMessageApi;
    @Mock
    KafkaMessageApiService kafkaMessageApiService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(kafkaMessageApi)
                .build();
    }

    @Test
    void POSTMessageToElasticSearch_Return201Created() throws Exception {
        //given
        String validJsonPayload = "{\"message\":\"test_message\"," +
                " \"UID\": \"12345\"" +
                ",\"topic\":" + "\"testTopicFoo\"}";
        doNothing().when(kafkaMessageApiService).postMessageToKafkaTopic(any(Message.class));
        //when
        mockMvc.perform(
                post("/KMApi/Post")
                        .content(validJsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andReturn();
        //then
        verify(kafkaMessageApiService, times(1)).postMessageToKafkaTopic(any(Message.class));
    }

//    @Test
//    void POSTInvalidMessageToElasticSearch_ReturnException() throws Exception {
//        //given
//        String InvalidJsonPayload = "{\"NOTVALID\":\"\"," +
//                " \"NOTVALID\": \"\"" +
//                ",\"NOTVLAID\":" + "\"\"}";
//        doNothing().when(kafkaMessageApiService).postMessageToKafkaTopic(any(Message.class));
//        //when
//        mockMvc.perform(
//                post("/KMApi/Post")
//                        .content(InvalidJsonPayload)
//                        .contentType(MediaType.APPLICATION_JSON))
//
//                .andExpect(status().isBadRequest())
//                .andReturn();
//        //then
//        verify(kafkaMessageApiService, times(0)).postMessageToKafkaTopic(any(Message.class));
//    }

    @Test
    void GETMessageFORMDatabase_returnMessageInDatabase() throws Exception {
        //given
        String searchWithUID = "{\"UID\":\"12345\"" +
               "}";
        Message testMessage = new Message();
        testMessage.setAnId("12345");
        testMessage.setMessage("testMessage");
        testMessage.setTopic("testTopic");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonMessageInTest = ow.writeValueAsString(testMessage);



        when(kafkaMessageApiService.getMessageFromElasticSearchViaUID(any(SearchWithUID.class))).thenReturn(testMessage);

        //when
        final MvcResult mvcResult = mockMvc.perform(
                get("/KMApi/Get")
                        .content(searchWithUID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        //then

        assertEquals(jsonMessageInTest,content,"Response body not as expected");
        verify(kafkaMessageApiService,times(1)).getMessageFromElasticSearchViaUID(any(SearchWithUID.class));
    }
//
//    @Test
//    void GETMessageFORMDatabaseWithInvalidRequest_returnBadRequest() throws Exception {
//        //given
//        String searchWithUID = "{\"INVALID\":\"\"" +
//                "}";
//        Message testMessage = new Message();
//        testMessage.setUID("12345");
//        testMessage.setMessage("testMessage");
//        testMessage.setTopic("testTopic");
//
//        when(kafkaMessageApiService.getMessageFromElasticSearchViaUID(any(SearchWithUID.class))).thenReturn(testMessage);
//
//        //when
//        final MvcResult mvcResult = mockMvc.perform(
//                get("/KMApi/Get")
//                        .content(searchWithUID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//
//        //then
//
//        verify(kafkaMessageApiService,times(0)).getMessageFromElasticSearchViaUID(any(SearchWithUID.class));
//    }

}