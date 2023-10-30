package ru.liga.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.config.RabbitConfiguration;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabitMqProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);
        log.info("Message has been sanded");
    }
}
