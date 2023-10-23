package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.models.Courier;
import ru.liga.models.Orders;
import ru.liga.repositoryes.CourierRepository;
import ru.liga.repositoryes.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;

    private final OrderMapper orderMapper;
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
    public Courier updateCourierById(Courier courier){
        return  courierRepository.save(courier);
    }
    public Courier deleteCourierById(Courier courier){
        return  courierRepository.save(courier);
    }

    public List<Orders> getOrderByStatus(String status) {
        System.out.println(status);
       return  orderMapper.getOrdersByStatus(status);
    }
    public List<Orders> getOrderByStatus2(String status) {
        return  orderRepository.getOrdersByStatusOrder(status);
    }
}
