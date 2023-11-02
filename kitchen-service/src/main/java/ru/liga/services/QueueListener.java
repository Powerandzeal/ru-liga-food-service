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
    @RabbitListener(queues = "QueueOrder")
    public void processMyQueue(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseOrderDTO messageModel = objectMapper.readValue(message, ResponseOrderDTO.class);
        System.out.println("Пришел заказ в ресторан ");
        System.out.println("Received from QueueOrder : " +  messageModel.getListOrder());
        System.out.println("Received from QueueOrder : " +  messageModel.getOrderPrice());
        System.out.println("Received from QueueOrder : " +  messageModel.getTimeCreate());
        System.out.println("Received from QueueOrder : " +  messageModel.getNameRestaurant());
        System.out.println("Received from QueueOrder : " +  messageModel.getOrderId());

    }
}
