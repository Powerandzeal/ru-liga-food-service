package ru.liga.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.liga.Enum.DeliveryStatusOrder;
import ru.liga.DTO.CreateCourierDTO;
import ru.liga.DTO.UpdateCourierDTO;
import ru.liga.Enum.OrderStatus;
import ru.liga.Exceptions.ValidationException;
import ru.liga.models.Courier;
import ru.liga.models.Orders;
import ru.liga.repositoryes.CourierRepository;
import ru.liga.repositoryes.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;

    private final OrderRepository orderRepository;

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

        Courier courier = new Courier();
        courier.setPhonenumber(courierDTO.getPhonenumber());
        courier.setName(courierDTO.getName());
        courier.setCoordinate(courierDTO.getCoordinate());
        courier.setStatusOrder(DeliveryStatusOrder.OFFLINE);

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
    public List<Orders> getOrderByStatus2(DeliveryStatusOrder status) {
        // Логирование действия
        log.info("Запрос заказов с статусом: {}", status);

        return orderRepository.getOrdersByOrderStatus(status);
    }

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

//    public List<Orders> getOrdersForDelivering() {
//
//    }

    public Orders deliveryPicking (Long orderId,Long courierId) {
        Courier courier = courierRepository.findById(courierId).orElseThrow(()->new
                NotFoundException("Курьер не найден"));
       Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Заказ не найден"));
       order.setCourier(courier);
        order.setOrderStatus(OrderStatus.DELIVERY_PICKING);
        return orderRepository.save(order);
    }
    public Orders deliveryIsDelivering(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Заказ не найден"));
        order.setOrderStatus(OrderStatus.DELIVERY_DELIVERING);
        return orderRepository.save(order);
    }
    public Orders deliveryIsFinished(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Заказ не найден"));
        order.setOrderStatus(OrderStatus.DELIVERY_COMPLETE);
        return orderRepository.save(order);
    }
    public Orders deniedDelivery (Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(()->new
                NotFoundException("Заказ не найден"));
        order.setOrderStatus(OrderStatus.DELIVERY_DENIED);
        return orderRepository.save(order);
    }
}
