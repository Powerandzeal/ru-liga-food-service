package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.models.Courier;
import ru.liga.services.CourierService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeliveryController {

    private final CourierService courierService;


    @PostMapping("/createCourier")
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        Courier createdCourier = courierService.createCourier(courier);
        return ResponseEntity.ok(createdCourier);
    }

    @GetMapping("gelAllCouriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {

        return ResponseEntity.ok(courierService.getAllCourier());

    }


    @GetMapping("/getCourierById")
    public ResponseEntity<Courier> getCourierById(@RequestParam Long courierId) {
        return ResponseEntity.ok(courierService.getCourierById(courierId));
    }

    // Update
    @PutMapping("/updateCourierById")
    public ResponseEntity<Courier> updateCourier(
            @PathVariable Long courierId,
            @RequestBody Courier updatedCourier
    ) {
//        Courier courier = courierService.updateCourier(courierId, updatedCourier);
        return null;
    }

    // Delete
    @DeleteMapping("/deleteCourierById")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long courierId) {
//        courierService.deleteCourier(courierId);
        return ResponseEntity.noContent().build();
    }
}
