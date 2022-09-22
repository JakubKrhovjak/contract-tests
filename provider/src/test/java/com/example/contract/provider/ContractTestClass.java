package com.example.contract.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@EnableWebMvc
@AutoConfigureMessageVerifier
public class ContractTestClass {

    @Autowired
    private CountController countController;

//    @Autowired
//    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(countController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

    }

//    @Test
//    void name() {
//    }
}
