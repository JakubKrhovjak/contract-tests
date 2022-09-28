package com.example.contract.consumer.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by Jakub Krhovják on 9/23/22.
 */

@Configuration
@RequiredArgsConstructor
public class InputMessagingConfiguration {

    private final MessagingService messagingService;

    @Bean
    public Consumer<Message<TestData>> simpleMessageReceiver() {
        return message -> messagingService.process(message.getPayload(), message.getHeaders());
    }

}
