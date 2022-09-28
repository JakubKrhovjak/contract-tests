package com.example.contract.provider.messaging;

import com.example.contract.provider.ApiController;

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
import reactor.core.publisher.Sinks;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@Slf4j
@Configuration
public class OutputMessagingConfiguration {


    @Bean
    public Sinks.Many<Message<ApiController.TestData>> simpleMessageSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }


    @Bean
    public Supplier<Flux<Message<ApiController.TestData>>> simpleMessageProducer(Sinks.Many<Message<ApiController.TestData>> sink) {
        return sink::asFlux;
    }


//    @Bean
//    public Consumer<Message<String>> simpleMessageReceiver() {
//        return message -> log.info(message.getPayload());
//    }

}
