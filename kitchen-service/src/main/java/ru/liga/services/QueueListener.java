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

        System.out.println("Пришел заказ в ресторан "+  messageModel.getNameRestaurant());
        System.out.println("Received from QueueOrder id заказа: " +  messageModel.getOrderId());
        System.out.println("Received from QueueOrder - список позиций");
        System.out.println("Received from QueueOrder - список позиций : " +  messageModel.getListOrder());
        System.out.println("Received from QueueOrder - оплаченная сумма : " +  messageModel.getOrderPrice());
        System.out.println("Received from QueueOrder дата и время создания заказа : " +  messageModel.getTimeCreate());
        System.out.println();

    }
    @SneakyThrows
    @RabbitListener(queues = "QueueOrder")
    public void processMyQueue2(String message) {


        System.out.println("Оповещение от курьера ");
        System.out.println("Received from NotificationOrder : " + message);
        System.out.println();


        System.out.println();

    }
}
