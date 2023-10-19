package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RegistrationCustomerDTO;
import ru.liga.models.Customers;
import ru.liga.services.CustomerService;

import java.util.Optional;

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
    @GetMapping("/orders")
    public Optional<Customers> getCustomerById(@RequestParam Long customerId ) {
           return customerService.getCustomerById(customerId);
        // Обработка GET-запроса /customer/orders
        // Возвращение HTTP-ответа
    }

    @GetMapping("/orders")
    public ResponseEntity<?> updateCustomer(@RequestParam Long customerId ) {
        // Обработка GET-запроса /customer/orders
        // Возвращение HTTP-ответа
        return ResponseEntity.ok(customerService.updateCustomerById(customerId));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> deleteCustomer(@RequestParam Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Успешно удалено");

    }


}
