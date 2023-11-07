package ru.liga.clientservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OAuth контроллер.
 *
 */
@RestController
public class OAuth2ResourceServerController {

    @GetMapping("/message")
    public String message() {
        return "Добро пожаловать в Лигу!";
    }

}