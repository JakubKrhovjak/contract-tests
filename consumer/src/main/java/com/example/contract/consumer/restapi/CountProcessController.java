package com.example.contract.consumer.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Jakub Krhovják on 9/22/22.
 */
@RestController
public class CountProcessController {

    @GetMapping("/calculate")
    public String checkOddAndEven(@RequestParam("number") Integer number) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(
                "http://localhost:8090/api/validate/prime-number?number=" + number,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }

}
