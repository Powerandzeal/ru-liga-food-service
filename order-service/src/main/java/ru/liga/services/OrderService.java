package ru.liga.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.DTO.ItemsDto;
import ru.liga.Enum.OrderStatus;
import ru.liga.dto.CreateOrderDTO;
import ru.liga.dto.RestourantMenuDTO;
import ru.liga.dto.RestourantMenuItemDTO;
import ru.liga.models.OrderItems;
import ru.liga.models.Orders;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.repositoryes.*;

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
   private final OrderItemsRepository orderItemsRepository;
//    public Orders createOrder(CreateOrderDTO orderDTO,Long customerId) {
//
//        Orders order = new Orders();
//
//        order.setCustomer(customerRepository.findById(customerId).orElseThrow());
//
//        List<OrderItems> listItems = new ArrayList<>();
//        List<ItemsDto > list1 = orderDTO.getItems();
//        System.out.println(list1);
//
//        for (int i = 0; i < orderDTO.getItems().size(); i++) {
//            System.out.println("размер количество позиций в заказе "+ orderDTO.getItems().size());
//            OrderItems orderItems = new OrderItems();
//            orderItems.setQuantity(list1.get(i).getQuantity());
//            orderItems.setPrice((int)calculateSumOrder(orderDTO));
//            orderItems.setRestaurantMenuItem(restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow());
//            orderItems.setOrderEntity(order);
//            orderItemsRepository.save(orderItems);
//            System.out.println(restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow());
//            RestaurantMenuItem restaurantMenuItem1 = restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow();
//            System.out.println(restaurantMenuItem1.toString());
////            orderItems.setRestaurantMenuItemId(restaurantMenuItem1);
//            listItems.add(orderItems);
//        }
//        System.out.println(calculateSumOrder(orderDTO));
//
////        order.setOrderItemsList(orderDTO.getItems());
//        order.setCreateOrderTime(Timestamp.valueOf(LocalDateTime.now()));
//
//        order.setOrderStatus(OrderStatus.CUSTOMER_CREATED);
//        order.setRestaurant(restaurantRepository.findById(orderDTO.getRestaurantId()).orElseThrow());
//
//        return ordersRepository.save(order);
//    }
public Orders createOrder(CreateOrderDTO orderDTO, Long customerId) {
    Orders order = new Orders();
    // Заполните поля заказа

    order.setCustomer(customerRepository.findById(customerId).orElseThrow());

    List<OrderItems> listItems = new ArrayList<>();
    List<ItemsDto> list1 = orderDTO.getItems();

    for (int i = 0; i < orderDTO.getItems().size(); i++) {
        OrderItems orderItems = new OrderItems();
        orderItems.setQuantity(list1.get(i).getQuantity());
        orderItems.setPrice((int) calculateSumOrder(orderDTO));
        orderItems.setRestaurantMenuItem(restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow());
        orderItems.setOrderEntity(order); // Установите заказ для элемента заказа
        listItems.add(orderItems);
    }

    order.setCreateOrderTime(Timestamp.valueOf(LocalDateTime.now()));
    order.setOrderStatus(OrderStatus.CUSTOMER_CREATED);
    order.setRestaurant(restaurantRepository.findById(orderDTO.getRestaurantId()).orElseThrow());
    order.setPrice(calculateSumOrder(orderDTO));

    //  сохранине заказа
    Orders savedOrder = ordersRepository.save(order);

    //  сохранение элемента заказа
    for (OrderItems orderItem : listItems) {
        orderItem.setOrderEntity(savedOrder);
        orderItemsRepository.save(orderItem);
    }

    return savedOrder;
}
//    public Orders createOrder(CreateOrderDTO orderDTO, Long customerId) {
//        Orders order = new Orders();
//        // Заполните поля заказа
//
//        order.setCustomer(customerRepository.findById(customerId).orElseThrow());
//
//        List<OrderItems> listItems = new ArrayList<>();
//        List<ItemsDto> list1 = orderDTO.getItems();
//
//        for (int i = 0; i < orderDTO.getItems().size(); i++) {
//            OrderItems orderItems = new OrderItems();
//            orderItems.setQuantity(list1.get(i).getQuantity());
//            orderItems.setPrice((int) calculateSumOrder(orderDTO));
//            orderItems.setRestaurantMenuItem(restaurantMenuItemsRepository.findById(list1.get(i).getRestaurantMenuItemsId()).orElseThrow());
//            orderItems.setOrderEntity(order); // Установите заказ для элемента заказа
//            listItems.add(orderItems);
//        }
//
//        order.setCreateOrderTime(Timestamp.valueOf(LocalDateTime.now()));
//        order.setOrderStatus(OrderStatus.CUSTOMER_CREATED);
//        order.setRestaurant(restaurantRepository.findById(orderDTO.getRestaurantId()).orElseThrow());
//        order.setPrice(calculateSumOrder(orderDTO));
//
//        //  сохранине заказа
//        Orders savedOrder = ordersRepository.save(order);
//
//        //  сохранение элемента заказа
//        for (OrderItems orderItem : listItems) {
//            orderItem.setOrderEntity(savedOrder);
//            orderItemsRepository.save(orderItem);
//        }
//
//        return savedOrder;
//    }

    public double calculateSumOrder(CreateOrderDTO orderDTO){
        double sum = 0;
        List<ItemsDto > list1 = orderDTO.getItems();
        System.out.println(list1.size());
        for (ItemsDto val: list1) {
            System.out.println(val);
          double price = restaurantMenuItemsRepository.findById(val.getRestaurantMenuItemsId()).orElseThrow().getPrice();
            sum  += val.getQuantity() * price;
        }
        return sum;
    }
//    public double calculateSumOrder(CreateOrderDTO orderDTO){
//        double sum = 0;
//        List<ItemsDto > list1 = orderDTO.getItems();
//
//        for (ItemsDto val: list1) {
//
//            double price = restaurantMenuItemsRepository.findById(val.getRestaurantMenuItemsId()).orElseThrow().getPrice();
//            sum  += val.getQuantity() * price;
//        }
//        return sum;
//    }

    public List<Restaurant> getAllRestaurant() {
        System.out.println("insideGetAllRestaurant");
       return restaurantRepository.findAll();
    }
    public RestourantMenuDTO findByIdRestaurant(Long id) {
        RestourantMenuDTO restourantMenuDTO = new RestourantMenuDTO();
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restourantMenuDTO.setName(restaurant.getNameRestaurant());
        restourantMenuDTO.setAddress(restaurant.getAddressKitchen());
        List<RestourantMenuDTO> menu = new ArrayList<>();
//        restourantMenuDTO.setMenu();
        return restourantMenuDTO;
    }
    public List<RestaurantMenuItem> getMenu(Long id) {
        return restaurantMenuItemsRepository.findByRestaurantId(id);
    }

    public List<RestourantMenuItemDTO> getMenu2(Long id) {
        List<RestourantMenuItemDTO> menu = new ArrayList<>();
        List <RestaurantMenuItem> menuItem = restaurantMenuItemsRepository.findByRestaurantId(id);
        for (RestaurantMenuItem v:
                menuItem ) {
            RestourantMenuItemDTO restourantMenuItemDTO = new RestourantMenuItemDTO();
            restourantMenuItemDTO.setDescription(v.getDescription());
            restourantMenuItemDTO.setName(v.getName());
            restourantMenuItemDTO.setImage(v.getImage());
            restourantMenuItemDTO.setPrice(v.getPrice());
            restourantMenuItemDTO.setId(v.getId());

            menu.add(restourantMenuItemDTO);
        }
        return menu;
    }
    public void changeOrder() {

    }
    public void getOrder() {

    }
    public void deleteOrder() {

    }
}
