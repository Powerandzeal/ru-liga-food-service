package ru.liga.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.DTO.NotificationOrdersForCourier;
import ru.liga.DTO.ResponseOrderDTO;

@Service
@RequiredArgsConstructor
public class QueueListener {
    @SneakyThrows
    @RabbitListener(queues = "QueueOrderForCourier")
    public void processMyQueue(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationOrdersForCourier messageModel = objectMapper.readValue(message, NotificationOrdersForCourier.class);

        System.out.println("Новый заказ " );
        System.out.println("Received from QueueOrder Адресс ретсорана: " +  messageModel.getAdressRestaurant());
        System.out.println("Received from QueueOrder - оплата за доставку" + messageModel.getIncome());
        System.out.println("Received from QueueOrder - Адрес покупателя : " +  messageModel.getAddresCustomer());
        System.out.println("Received from QueueOrder - Ожимадаемое время доставки " + messageModel.getTimeDelivery()+ " минут" );
        System.out.println();

    }
}
