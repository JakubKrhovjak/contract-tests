package com.example.contract.provider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by Jakub Krhovják on 9/24/22.
 */

@SpringBootTest(classes = { ProviderApplication.class, TestChannelBinderConfiguration.class })
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    protected OutputDestination output;

    @Autowired
    private MockMvc mvc;

    @Test
    void sendMessage() throws Exception {
        mvc.perform(get("/api/test"))
                .andExpect(status().is2xxSuccessful());

        final var firstMessage = output.receive(1000, "consumer-event.destination");
        assertThat(new String(firstMessage.getPayload())).isEqualTo("test");

    }
}
