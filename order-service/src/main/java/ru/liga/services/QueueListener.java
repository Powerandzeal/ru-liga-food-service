package ru.liga.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.DTO.ResponseOrderDTO;

@Service
@RequiredArgsConstructor
public class QueueListener {
    @SneakyThrows
    @RabbitListener(queues = "NotificationCustomer")
    public void processMyQueue(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
//        ResponseOrderDTO messageModel = objectMapper.readValue(message, ResponseOrderDTO.class);

        System.out.println("Оповещение от ресторана ");
        System.out.println("Received from NotificationOrder : " + message);
        System.out.println();

    }
    @SneakyThrows
    @RabbitListener(queues = "NotificationCustomerFromCourier")
    public void processMyQueue1(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
//        ResponseOrderDTO messageModel = objectMapper.readValue(message, ResponseOrderDTO.class);

        System.out.println("Оповещение от курьера ");
        System.out.println("Received from NotificationCourier : " + message);
        System.out.println();

    }
}
