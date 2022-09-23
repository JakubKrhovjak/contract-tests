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

    private final Flux<Message<String>> simpleMessageSupplier;


    public OutputMessagingConfiguration() {
         simpleMessageSupplier = Flux.create(sink -> simpleMessageChannel = sink::next);
    }

    @Bean
    public SimpleMessageChannel simpleMessageChanel() {
        return d -> simpleMessageChannel.accept(d);
    }

    @Bean
    public Supplier<Flux<Message<String>>> simpleMessageProducer() {
        return () -> simpleMessageSupplier;
    }

//
//    Random random = new Random();

//    @Bean
//    public Function<String, Message<String>> simpleMessageProducer() {
//        return message -> {
//            return MessageBuilder.withPayload(message).build();
//        };
//    }

//    @Bean
//    public Function<String, Flux<Integer>> simpleMessageProducer() {
//        return (a) -> Flux.interval(Duration.ofSeconds(5)).map(value -> random.nextInt(1000 - 1) + 1).log();
//    }

//    @Bean
//    public Consumer<Message<String>> simpleMessageReceiver() {
//        return message -> {
//            log.info("");
//        };
//    }

//    @Bean
//    public Function<Flux<Integer>, Flux<String>> fizzBuzzProcessor(){
//        return longFlux -> longFlux
//                .map(i -> evaluateFizzBuzz(i))
//                .log();
//    }
//
//
//    private String evaluateFizzBuzz(Integer value) {
//        if (value % 15 == 0) {
//            return "FizzBuzz";
//        } else if (value % 5 == 0) {
//            return "Buzz";
//        } else if (value % 3 == 0) {
//            return "Fizz";
//        } else {
//            return String.valueOf(value);
//        }
//    }

}
