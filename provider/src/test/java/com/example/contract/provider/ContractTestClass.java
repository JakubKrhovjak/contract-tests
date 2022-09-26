package com.example.contract.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */
//set up explicit mode
@SpringBootTest(classes = { ProviderApplication.class, TestChannelBinderConfiguration.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureMessageVerifier
public class ContractTestClass {

    @Autowired
    ApiController controller;
    @Autowired
    private WebApplicationContext webAppContext;

    @PostConstruct
    public void init() {
        RestAssuredMockMvc.webAppContextSetup(webAppContext);
    }

    public void triggerSendMessage() throws Exception {
        controller.testMessage();
    }

}
