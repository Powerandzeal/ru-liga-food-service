package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.models.Courier;
import ru.liga.services.CurrierService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeliveryController {

    private final CurrierService currierService;


    @PostMapping("/createCourier")
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        Courier createdCourier = currierService.createCourier(courier);
        return ResponseEntity.ok(createdCourier);
    }

    @GetMapping("gelAllCouriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {
//        List<Courier> couriers = courierService.getAllCouriers();
        return
//                ResponseEntity.ok(couriers)
                null;
    }


    @GetMapping("/getCourier")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long courierId) {
//        Courier courier = courierService.getCourierById(courierId);
        return null;
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
