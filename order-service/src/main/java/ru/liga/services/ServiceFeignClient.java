package ru.liga.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.dto.ResponseOrderDTO;

@FeignClient(name = "notifcation-service", url = "http://localhost:8083/")
public interface ServiceFeignClient {
    @PostMapping("/sendNotification")
     void sendNotification(@RequestBody ResponseOrderDTO notification);
}
