package com.example.contract.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jakub Krhovják on 9/22/22.
 */

@RestController
public class CountController {

    @GetMapping("/validate/prime-number")
    public String isNumberPrime(@RequestParam("number") Integer number) {
        System.out.println("____________________________________________________");
        return number % 2 == 0 ? "Even" : "Odd";
    }
}
