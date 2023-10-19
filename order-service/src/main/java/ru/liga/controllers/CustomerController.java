package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RegistrationCustomerDTO;
import ru.liga.models.Customers;
import ru.liga.models.Order;
import ru.liga.services.CustomerService;
import ru.liga.services.OrderService;
@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
        @PostMapping("/registrationCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody RegistrationCustomerDTO registrationCustomerDTO) {
            return ResponseEntity.ok(customerService.createCustomer(registrationCustomerDTO));
        // Обработка POST-запроса /customer/order
        // Возвращение HTTP-ответа
    }
//    @GetMapping("/orders")
//    public ResponseEntity<?> getOrders() {
//        // Обработка GET-запроса /customer/orders
//        // Возвращение HTTP-ответа
//    }
//
//    @GetMapping("/order/{id}")
//    public ResponseEntity<?> getOrder(@PathVariable Long id) {
//        // Обработка GET-запроса /customer/order/{id}
//        // Возвращение HTTP-ответа
//    }
//

}
