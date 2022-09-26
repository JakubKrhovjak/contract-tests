package com.example.contract.provider;

import com.example.contract.provider.messaging.SimpleMessageChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Sinks;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/validate/prime-number")
    public String isNumberPrime(@RequestParam("number") Integer number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }


    @Autowired
    private Sinks.Many<Message<String>> simpleMessageSink;



    @GetMapping("/test")
    public void testMessage() {
        simpleMessageSink.tryEmitNext(MessageBuilder.withPayload("test").setHeader("testHeader", "testValue").build());
    }
}
