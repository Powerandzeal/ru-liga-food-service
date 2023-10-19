package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.models.Courier;
import ru.liga.repositoryes.CourierRepository;

@Service
@AllArgsConstructor
public class CurrierService {
    CourierRepository courierRepository;
    public Courier createCourier(Courier courier){
        return  courierRepository.save(courier);
    }
    public Courier getCourierById (Long courierId){
        return  courierRepository.getReferenceById(courierId);
    }
    public Courier getAllCourier(Courier courier){
        return  courierRepository.save(courier);
    }
    public Courier updateCourierById(Courier courier){
        return  courierRepository.save(courier);
    }
    public Courier deleteCourierById(Courier courier){
        return  courierRepository.save(courier);
    }

}
