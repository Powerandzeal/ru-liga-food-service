package ru.liga.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class RabitMqProducerKitchen {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotificationCustomer(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);

        log.info("Order has been sanded notification by customer  RabbitMq");
    }
    public void sendNotificationForCouriers(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);

        log.info("Order has been sanded notification by customer  RabbitMq");
    }

}
