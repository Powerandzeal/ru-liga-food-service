package ru.liga.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.DTO.ItemsDto;
import ru.liga.Enum.CustomerStatusOrder;
import ru.liga.Enum.DeliveryStatusOrder;
import ru.liga.Enum.OrderStatus;
import ru.liga.dto.CreateOrderDTO;
import ru.liga.models.OrderItems;
import ru.liga.models.Orders;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.repositoryes.CustomerRepository;
import ru.liga.repositoryes.OrdersRepository;
import ru.liga.repositoryes.RestaurantMenuItemsRepository;
import ru.liga.repositoryes.RestaurantRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
   private final OrdersRepository ordersRepository;
   private final RestaurantRepository restaurantRepository;
   private final RestaurantMenuItemsRepository restaurantMenuItemsRepository;
   private final CustomerRepository customerRepository;
    public Orders createOrder(CreateOrderDTO orderDTO,Long customerId) {
        Orders order = new Orders();

        order.setCustomer(customerRepository.findById(customerId).orElseThrow());

        List<OrderItems> listItems = new ArrayList<>();
        List<ItemsDto > list1 = orderDTO.getItems();
        System.out.println(list1);

        for (int i = 0; i < orderDTO.getItems().size(); i++) {
            OrderItems orderItems = new OrderItems();
            orderItems.setQuantity(list1.get(i).getQuantity());
            orderItems.setPrice(calculateSumOrder(orderDTO));
            System.out.println(restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow());
            RestaurantMenuItem restaurantMenuItem1 = restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow();
            System.out.println(restaurantMenuItem1.toString());

//            orderItems.setRestaurantMenuItemId(restaurantMenuItem1);
            listItems.add(orderItems);
        }
        System.out.println(calculateSumOrder(orderDTO));

//        order.setOrderItemsList(orderDTO.getItems());
        order.setCreateOrderTime(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderItemsList(listItems);
        order.setOrderStatus(OrderStatus.CUSTOMER_CREATED);
//        order.setCourier(null);
        order.setRestaurant(restaurantRepository.findById(orderDTO.getRestaurantId()).orElseThrow());
//        order.setTimeDelivery(null);

        return ordersRepository.save(order);
    }
    public double calculateSumOrder(CreateOrderDTO orderDTO){
        double sum = 0;
        List<ItemsDto > list1 = orderDTO.getItems();
        for (ItemsDto val: list1) {

          double price =  restaurantMenuItemsRepository.findById(val.getRestaurantMenuItemsId()).orElseThrow().getPrice();
            sum  += val.getQuantity() * price;
        }
        return sum;
    }
    public void changeOrder() {

    }
    public void getOrder() {

    }
    public void deleteOrder() {

    }
}
