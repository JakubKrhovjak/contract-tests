package com.example.contract.provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Sinks;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@RestController
@RequestMapping("/api")
public class ApiController {
    private static ObjectMapper OB = new ObjectMapper();

    @GetMapping("/validate/prime-number")
    public String isNumberPrime(@RequestParam("number") Integer number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }


    @Autowired
    private Sinks.Many<Message<ApiController.TestData>> simpleMessageSink;



    @GetMapping("/test")
    public void testMessage() {
        simpleMessageSink.tryEmitNext(MessageBuilder.withPayload(new TestData("name",  "testName")).setHeader("testHeader", "testValue").build());
    }

    @Data
    @RequiredArgsConstructor
    public static class TestData {
        private final String key;
        private final String value;
    }
}
