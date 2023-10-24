package ru.liga.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.Courier.UpdateCourierDTO;
import ru.liga.models.Courier;
import ru.liga.models.Orders;
import ru.liga.repositoryes.CourierRepository;
import ru.liga.repositoryes.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {
    private final CourierRepository courierRepository;

    private final OrderRepository orderRepository;
    public Courier createCourier(Courier courier){
        return  courierRepository.save(courier);
    }
    public Courier getCourierById (Long courierId){
        return  courierRepository.findById(courierId).orElseThrow();
    }
    public List<Courier> getAllCourier(){
        return  courierRepository.findAll();
    }
    public Courier updateCourierById(Long id, UpdateCourierDTO updateCourierDTO){
        log.info("updateCourierById вызвался");
        Courier courier = courierRepository.findById(id).orElseThrow();
        courier.setCoordinate(updateCourierDTO.getCoordinate());
        courier.setName(updateCourierDTO.getName());
        courier.setPhonenumber(updateCourierDTO.getPhonenumber());
        return  courierRepository.save(courier);
    }
    public void deleteCourierById(Long id){
          courierRepository.deleteById(id);
    }

    public List<Orders> getOrderByStatus2(String status) {
        return  orderRepository.getOrdersByStatusOrder(status);
    }
}
