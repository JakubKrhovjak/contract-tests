package com.example.contract.consumer.messaging;

import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@Slf4j
@Service
public class MessagingService {

    public void process(String data, MessageHeaders headers) {
        log.info("Data: {}", data);
        log.info("Headers: {}", headers.get("testHeader"));
    }
}
