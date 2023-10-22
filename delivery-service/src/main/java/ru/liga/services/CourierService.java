package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.models.Courier;
import ru.liga.repositoryes.CourierRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CourierService {
    CourierRepository courierRepository;
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

}
