package ru.liga.fiegn;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.models.Courier;
import ru.liga.models.Orders;

import java.util.List;

@FeignClient(name ="notification"  , url = "http://localhost:8081/courier")
public interface FeignService {
    @PostMapping("/create-courier")
    void sendNotification (@RequestBody Courier courier);

    @RequestMapping("/get-all-couriers")
    void getCourierByStatus ();

}
