package ru.liga.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingMqConfig {
    @Bean
    public Declarables notificationCustomerFromKitchenQueue() {
        Queue queueDirectFirst = new Queue("NotificationCustomer", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("order.customer"));
    }
    @Bean
    public Declarables notificationCustomerFromCourier() {
        Queue queueDirectFirst = new Queue("NotificationCustomerFromCourier", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("courier.order"));
    }
}
