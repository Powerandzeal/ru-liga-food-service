package ru.liga.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.models.Courier;

import java.util.List;

@RestController
public class DeliveryController {
    @PostMapping
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
//        Courier createdCourier = courierService.createCourier(courier);
        return
//                ResponseEntity.ok(createdCourier)
              null  ;
    }

    @GetMapping
    public ResponseEntity<List<Courier>> getAllCouriers() {
//        List<Courier> couriers = courierService.getAllCouriers();
        return
//                ResponseEntity.ok(couriers)
                null;
    }


    @GetMapping("/{courierId}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long courierId) {
//        Courier courier = courierService.getCourierById(courierId);
        return null;
    }

    // Update
    @PutMapping("/{courierId}")
    public ResponseEntity<Courier> updateCourier(
            @PathVariable Long courierId,
            @RequestBody Courier updatedCourier
    ) {
//        Courier courier = courierService.updateCourier(courierId, updatedCourier);
        return null;
    }

    // Delete
    @DeleteMapping("/{courierId}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long courierId) {
//        courierService.deleteCourier(courierId);
        return ResponseEntity.noContent().build();
    }
}
