package ru.liga.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.liga.DTO.NotificationOrdersForCourier;
import ru.liga.DTO.ResponseOrderDTO;
import ru.liga.Enum.DeliveryStatusOrder;
import ru.liga.DTO.CreateCourierDTO;
import ru.liga.DTO.UpdateCourierDTO;
import ru.liga.Enum.OrderStatus;
import ru.liga.Exceptions.ValidationException;
import ru.liga.Enum.Role;
import ru.liga.models.Courier;
import ru.liga.models.Orders;
import ru.liga.models.Users;
import ru.liga.repositoryes.CourierRepository;
import ru.liga.repositoryes.OrderRepository;
import ru.liga.repositoryes.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;

    private final OrderRepository orderRepository;
    private final RabbitMqProducerDelivery rabbitMqProducerDelivery;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Создает нового курьера на основе переданных данных.
     *
     * @param courierDTO Данные для создания курьера.
     * @return Созданный курьер.
     * @throws ValidationException если данные для создания курьера некорректны.
     */
    public Courier createCourier(CreateCourierDTO courierDTO) throws ValidationException {
        // Логирование действия
        log.info("Создание нового курьера");

        // Создаем и сохраняем пользователя
        Users users = new Users();
        users.setPassword(passwordEncoder.encode(courierDTO.getPassword()));
        users.setUsername(courierDTO.getPhonenumber());
        users.setRoleUser(Role.COURIER);
        users = userRepository.save(users); // Сохраняем и получаем пользователя с id

        // Создаем и сохраняем курьера
        Courier courier = new Courier();
        courier.setPhonenumber(courierDTO.getPhonenumber());
        courier.setName(courierDTO.getName());
        courier.setCoordinate(courierDTO.getCoordinate());
        courier.setStatusOrder(DeliveryStatusOrder.OFFLINE);
        courier.setUsers(userRepository.findByUsername(users.getUsername())); // Используем id пользователя

        return courierRepository.save(courier);
    }


    /**
     * Получает информацию о курьере по его идентификатору.
     *
     * @param courierId Идентификатор курьера.
     * @return Информация о курьере в виде ResponseEntity.
     */
//    public ResponseEntity<Courier> getCourierById(Long courierId) {
//        // Логирование действия
//        log.info("Запрос информации о курьере с ID: {}", courierId);
//
//        Optional<Courier> courier = courierRepository.findById(courierId);
//
//        if (courier.isPresent()) {
//            return ResponseEntity.ok(courier.get());
//        } else {
//            // Логирование ошибки
//            log.error("Курьер с ID {} не найден.", courierId);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    //
    public ResponseEntity<Courier> getCourierById(Long courierId) {
        // Логирование действия
        log.info("Запрос информации о курьере с ID: {}", courierId);

        return ResponseEntity.ok(courierRepository.findById(courierId).orElseThrow(()->new
                NotFoundException("Пользователь не найден")));
    }
    public ResponseEntity <List<ResponseOrderDTO>> getFreeOrders(Long courierId) {
        // Логирование действия
        log.info("Запрос информации о свободных заказов с ID: {}", courierId);

        return null;
    }
    //Варинат
//    public ResponseEntity<Courier> getCourierById(Long courierId) {
//        // Логирование действия
//        log.info("Запрос информации о курьере с ID: {}", courierId);
//
//        return ResponseEntity.ok(courierRepository.findById(courierId).orElseThrow());
//    }



    /**
     * Получает список всех курьеров.
     *
     * @return Список всех курьеров.
     */
    public List<Courier> getAllCourier() {
        // Логирование действия
        log.info("Запрос списка всех курьеров");

        return courierRepository.findAll();
    }

    /**
     * Обновляет информацию о курьере по его идентификатору.
     *
     * @param id               Идентификатор курьера.
     * @param updateCourierDTO Обновленные данные о курьере.
     * @return Обновленный курьер.
     */
    public Courier updateCourierById(Long id, UpdateCourierDTO updateCourierDTO) {
        // Логирование действия
        log.info("Обновление информации о курьере с ID: {}", id);

        Courier courier = courierRepository.findById(id).orElseThrow();
        courier.setCoordinate(updateCourierDTO.getCoordinate());
        courier.setName(updateCourierDTO.getName());
        courier.setPhonenumber(updateCourierDTO.getPhonenumber());
        return courierRepository.save(courier);
    }

    /**
     * Удаляет курьера по его идентификатору.
     *
     * @param id Идентификатор курьера.
     */
    public void deleteCourierById(Long id) {
        // Логирование действия
        log.info("Удаление курьера с ID: {}", id);

        courierRepository.deleteById(id);
    }

    /**
     * Получает список заказов по статусу.
     *
     * @param status Статус заказов.
     * @return Список заказов с указанным статусом.
     */
//    public List<Orders> getOrderByStatus2(DeliveryStatusOrder status) {
//        // Логирование действия
//        log.info("Запрос заказов с статусом: {}", status);
//
//        return orderRepository.getOrdersByOrderStatus(status);
//    }

    /**
     * Получает список заказов, принадлежащих конкретному курьеру.
     *
     * @param courierId Идентификатор курьера.
     * @return Список заказов, принадлежащих указанному курьеру.
     */
    public List<Orders> getAllCourierOrders(Long courierId) {
        // Логирование действия
        log.info("Запрос заказов для курьера с ID: {}", courierId);

        return orderRepository.getOrdersByCourier_Id(courierId);
    }
    public List<NotificationOrdersForCourier> getFreeOrders() {

        // Логирование действия
        log.info("Запрос заказов для курьера с ID: {}");
       List<Orders> list =  orderRepository.getOrdersByOrderStatus(OrderStatus.KITCHEN_PREPARING);
       List<NotificationOrdersForCourier> freeOrder =  new ArrayList<>();

        for (Orders v : list
        ) {
            if(v.getOrderStatus().equals(OrderStatus.KITCHEN_PREPARING)) {
                NotificationOrdersForCourier notificationOrdersForCourier = new NotificationOrdersForCourier();
                notificationOrdersForCourier.setOrderId(v.getId());
                notificationOrdersForCourier.setAddresCustomer(v.getCustomer().getAddress());
                notificationOrdersForCourier.setIncome(v.getPrice() / 4);
                notificationOrdersForCourier.setAdressRestaurant(v.getRestaurant().getAddressKitchen());
                notificationOrdersForCourier.setTimeDelivery(v.getTimeDelivery());
                notificationOrdersForCourier.setNameRestaurant(v.getRestaurant().getNameRestaurant());
                freeOrder.add(notificationOrdersForCourier);
            }
        }
        return freeOrder;
    }


//    public List<Orders> getOrdersForDelivering() {
//
//    }

    public Orders deliveryPicking (Long orderId, Authentication authentication) {

        Courier courier = courierRepository.findByPhonenumber(authentication.getName()).orElseThrow(()->new
                NotFoundException("Курьер не найден"));
        log.info("Взятие заказа №" +orderId+"курьером  "+ courier);
       Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Заказ не найден"));
       order.setCourier(courier);
        order.setOrderStatus(OrderStatus.DELIVERY_PICKING);
        rabbitMqProducerDelivery.sendNotificationCustomerFromCourier("Заказ принят курьеров и направляется " +
                "к вам.Ожидаемое время доставки через 1:15","courier.order");
        return orderRepository.save(order);
    }
//    public Orders deliveryIsDelivering(Long orderId) {
//        Orders order = orderRepository.findById(orderId).orElseThrow(()->new
//                NotFoundException("Доставка в пути"));
//        order.setOrderStatus(OrderStatus.DELIVERY_DELIVERING);
//        rabbitMqProducerDelivery.sendNotificationCustomerFromCourier("Заказ в пути","customer.courier");
//        return orderRepository.save(order);
//    }
    public Orders deliveryIsFinished(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Доставка завершена"));
        order.setOrderStatus(OrderStatus.DELIVERY_COMPLETE);
        rabbitMqProducerDelivery.sendNotificationCustomerFromCourier("Доставка заказа №"+orderId+" завершена" +
                "","customer.courier");

        return orderRepository.save(order);
    }
    public Orders deniedDelivery (Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Заказ был отменен курьером,ищем другого курьера"));
        order.setOrderStatus(OrderStatus.DELIVERY_DENIED);
        return orderRepository.save(order);
    }
    public Users findByNumber (String phoneNumber) {
        // Логирование действия
        log.info("поиск курьера по номеру телефона");

        return userRepository.findByUsername(phoneNumber);
    }
}
