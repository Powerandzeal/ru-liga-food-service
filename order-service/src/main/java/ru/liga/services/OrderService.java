package ru.liga.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.DTO.ItemsDto;
import ru.liga.Enum.OrderStatus;
import ru.liga.dto.CreateOrderDTO;
import ru.liga.dto.ResponseOrderDTO;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
   private final OrdersRepository ordersRepository;
   private final RestaurantRepository restaurantRepository;
   private final RestaurantMenuItemsRepository restaurantMenuItemsRepository;
   private final CustomerRepository customerRepository;
   private final OrderItemsRepository orderItemsRepository;

   private final RabitMqProducer rabitMqProducer;
   private final ObjectMapper objectMapper;

public ResponseOrderDTO createOrder(CreateOrderDTO orderDTO, Long customerId) {
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

    // Сначала сохраните заказ
    Orders savedOrder = ordersRepository.save(order);

    // Затем сохраните элементы заказа
    for (OrderItems orderItem : listItems) {
        orderItem.setOrderEntity(savedOrder);
        orderItemsRepository.save(orderItem);
    }

    // Теперь получите необходимую информацию и создайте ResponseOrderDTO
    ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
    responseOrderDTO.setOrderId(savedOrder.getId());
    responseOrderDTO.setNameRestaurant(savedOrder.getRestaurant().getNameRestaurant()); // Предположим, что у ресторана есть поле "name"
    // Также получите список заказанных элементов и рассчитайте общую стоимость
    List<RestourantMenuItemDTO> listOrder = new ArrayList<>();
    double orderPrice = calculateSumOrder(orderDTO);

    for (OrderItems orderItem : listItems) {
        RestourantMenuItemDTO menuItemDTO = new RestourantMenuItemDTO();
        // Установите информацию о заказанном элементе меню в menuItemDTO
         menuItemDTO.setId(orderItem.getRestaurantMenuItem().getId());
         menuItemDTO.setName(orderItem.getRestaurantMenuItem().getName());
         menuItemDTO.setPrice(orderItem.getRestaurantMenuItem().getPrice());
//         Добавьте menuItemDTO в listOrder
        listOrder.add(menuItemDTO);


    }

    responseOrderDTO.setListOrder(listOrder);
    responseOrderDTO.setOrderPrice(orderPrice);
    responseOrderDTO.setTimeCreate(savedOrder.getCreateOrderTime());

    return responseOrderDTO;
}
    private void sendOrderInfoViaRabbitMQ(Orders order) {
        try {
            String orderInfoInLine = objectMapper.writeValueAsString(order);
            rabitMqProducer.sendMessage(orderInfoInLine, "your_queue_name");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Orders getOrderById(Long id) {

    return ordersRepository.findById(id).orElseThrow();
    }
    public ResponseOrderDTO getAllOrderByUser() {
        return null ;
    }
    public String payOrder(Long id) {
    Orders order =  ordersRepository.findById(id).orElseThrow();
        System.out.println(order);
    order.setOrderStatus(OrderStatus.CUSTOMER_PAID);
    ordersRepository.save(order);
        return "Заказ успешно оплачен" ;
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
