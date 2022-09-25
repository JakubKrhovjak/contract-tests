package com.example.contract.consumer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@SpringBootTest( classes = { ConsumerApplication.class, TestChannelBinderConfiguration.class },
                 webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.example.contract:provider:+:stubs:8090"
)
@ExtendWith(OutputCaptureExtension.class)
public class CountProcessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InputDestination input;

    @Autowired
    StubTrigger trigger;

    @Test
    public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven()
            throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate?number=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Even"));
    }

    @Test
    public void inputMessageTest(CapturedOutput log) throws Exception {
        input.send(MessageBuilder.withPayload("test").build(),"consumer-event.destination");

        assertThat(log.getOut()).contains("Data: test");
    }

    @Test
    public void inputMessageContractTest(CapturedOutput log) {
        this.trigger.trigger("simpleMessageContract");

        String out = new String(log.getOut());
        assertThat(log.getOut()).contains("Data: \"test\"");
        assertThat(log.getOut()).contains("testValue");

    }




}
