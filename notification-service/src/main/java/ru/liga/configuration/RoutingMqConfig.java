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
    @Bean
    public Declarables myQueue() {
        Queue queueDirectFirst = new Queue("QueueOrder", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("order.it"));
    }
    @Bean
    public Declarables queueForCourier() {
        Queue queueDirectFirst = new Queue("QueueOrderForCourier", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("order.courier"));
    }

//@Bean
//public Declarables myQueueWithMultipleRoutingKeys() {
//    Queue queueDirectFirst = new Queue("MyQueue", false);
//    DirectExchange directExchange = new DirectExchange("directExchange");
//
//    return new Declarables(
//            queueDirectFirst, directExchange,
//            BindingBuilder.bind(queueDirectFirst).to(directExchange).with("order.customer"),
//            BindingBuilder.bind(queueDirectFirst).to(directExchange).with("courier.order"),
//            BindingBuilder.bind(queueDirectFirst).to(directExchange).with("order.it")
//    );
//}
}
