package com.example.contract.provider.messaging;

import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * Created by Jakub Krhovják on 9/23/22.
 */
public interface SimpleMessageChannel extends Consumer<Message<String>> {

}
