package com.example.contract.provider.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.time.Duration;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@Slf4j
@Configuration
public class OutputMessagingConfiguration {

   private SimpleMessageChannel simpleMessageChannel;

    @Bean
    public SimpleMessageChannel simpleMessageChanel() {
        return d -> simpleMessageChannel.accept(d);
    }

    @Bean
    public Supplier<Flux<Message<String>>> simpleMessageProducer() {
        return () -> Flux.create(sink -> simpleMessageChannel = sink::next);
    }

//    @Bean
//    public Consumer<Message<String>> simpleMessageReceiver() {
//        return message -> log.info(message.getPayload());
//    }

}
