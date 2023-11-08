package ru.liga.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqProducerDelivery {
    private final RabbitTemplate rabbitTemplate;

    public void sendNotificationCustomerFromCourier(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);

        log.info("Courier has been sanded notification by customer  RabbitMq");
    }
//    public void sendNotificationKithcen(String message, String routingKey) {
//        rabbitTemplate.convertAndSend("directExchange", routingKey, message);
//
//        log.info("Courier has been sanded notification by kithen  RabbitMq");
//    }

}
