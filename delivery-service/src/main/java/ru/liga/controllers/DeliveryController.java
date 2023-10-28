package ru.liga.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.Courier.CreateCourierDTO;
import ru.liga.dto.Courier.UpdateCourierDTO;
import ru.liga.models.Courier;
import ru.liga.services.CourierService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/courier")
@Slf4j
public class DeliveryController {

    private final CourierService courierService;


    @PostMapping("/create-courier")
    public ResponseEntity<Courier> createCourier(@RequestBody CreateCourierDTO courier) {
        Courier createdCourier = courierService.createCourier(courier);
        return ResponseEntity.ok(createdCourier);
    }

    @GetMapping("get-all-couriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {

        return ResponseEntity.ok(courierService.getAllCourier());

    }


    @GetMapping("/{courierId}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long courierId) {
        return ResponseEntity.ok(courierService.getCourierById(courierId));
    }

    // Update
    @PutMapping("/{courierId}")
    public ResponseEntity<String> updateCourier(@PathVariable Long courierId,
                                                 @RequestBody UpdateCourierDTO updateCourierDTO
    ) {
        log.info("updateCourierById ");
        courierService.updateCourierById(courierId, updateCourierDTO);
        return ResponseEntity.ok("Курьер обновлен");
    }

    // Delete
    @DeleteMapping("/{courierId}")
    public ResponseEntity<String> deleteCourier(@PathVariable Long courierId) {
        courierService.deleteCourierById(courierId);
        return ResponseEntity.ok("Курьер удален");
    }
}
