package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.DTO.RegistrationCustomerDTO;
import ru.liga.models.Customers;
import ru.liga.services.CustomerService;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    // Создание нового заказчика.
    @Operation(
            summary = "Зарегистрировать пользователя",
            description = "Создает нового заказчика."
    )
    @PostMapping("/registrationCustomer")
    public ResponseEntity<?> createCustomer(
            @RequestBody RegistrationCustomerDTO registrationCustomerDTO
    ) {
        return ResponseEntity.ok(customerService.createCustomer(registrationCustomerDTO));
    }

    // Получение информации о заказчике по его идентификатору.
    @Operation(
            summary = "Получить заказчика по идентификатору",
            description = "Получает информацию о заказчике по его идентификатору."
    )
    @GetMapping("/getCustomerById")
    public ResponseEntity<Customers> getCustomerById(
            @RequestParam Long customerId
    ) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    // Обновление информации о заказчике по его идентификатору.
    @Operation(
            summary = "Обновить заказчика",
            description = "Обновляет информацию о заказчике по его идентификатору."
    )
    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(
            @RequestParam Long customerId
    ) {
        return ResponseEntity.ok(customerService.updateCustomerById(customerId));
    }

    // Удаление заказчика по его идентификатору.
    @Operation(
            summary = "Удалить заказчика",
            description = "Удаляет заказчика по его идентификатору."
    )
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteCustomer(
            @RequestParam Long id
    ) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Успешно удалено");
    }

    // Получение всех заказов заказчика по его идентификатору.
    @Operation(
            summary = "Получить все заказы заказчика",
            description = "Получает список всех заказов заказчика по его идентификатору."
    )
    @GetMapping("/getAllOrders")
    public Optional<Customers> getAllOrdersUser(
            @RequestParam Long customerId
    ) {
        return Optional.ofNullable(customerService.getCustomerById(customerId));
    }


}
