package com.example.contract.provider;

import com.example.contract.provider.messaging.SimpleMessageChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.function.Function;

import javax.annotation.PostConstruct;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/validate/prime-number")
    public String isNumberPrime(@RequestParam("number") Integer number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }


    @Autowired
    private SimpleMessageChannel simpleMessageChannel;


    @GetMapping("/test")
    public void test() {
        simpleMessageChannel.accept(MessageBuilder.withPayload("test").build());
    }
}
