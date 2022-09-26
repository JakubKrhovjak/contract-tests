package com.example.contract.provider.messaging;

import org.springframework.messaging.Message;

import java.util.function.Consumer;

import reactor.core.publisher.Sinks;

/**
 * Created by Jakub Krhovják on 9/23/22.
 */
public interface SimpleMessageChannel extends Sinks.Many<Message<String>> {

}
