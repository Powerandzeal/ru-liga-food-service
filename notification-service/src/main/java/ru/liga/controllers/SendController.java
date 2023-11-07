package ru.liga.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.ResponseOrderDTO;

@RestController
public class SendController {
    @PostMapping("/sendNotification")
    public void sendNotification(@RequestBody ResponseOrderDTO notification) {
        System.out.println("Отправляем оповещением о созданом заказе " + notification);
    }
    @PostMapping("/orderAcceptetForCustomer")
    public void sendNotificationCustomer(@RequestBody ResponseOrderDTO notification) {
        System.out.println("Ваш заказ был принят рестораном " + notification);
    }
}
