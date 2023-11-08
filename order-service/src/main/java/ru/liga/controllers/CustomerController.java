package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.DTO.RegistrationCustomerDTO;
import ru.liga.models.Customers;
import ru.liga.services.CustomerService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    // Создание нового заказчика.
    @Operation(
            summary = "Зарегистрировать пользователя",
            description = "Создает нового заказчика."
    )

    @PostMapping()
    public ResponseEntity<Customers> createCustomer(
            @RequestBody @Valid RegistrationCustomerDTO registrationCustomerDTO
    ) {
        log.info("Создание пользователя");
        return ResponseEntity.ok(customerService.createCustomer(registrationCustomerDTO));
    }

    // Получение информации о заказчике по его идентификатору.
    @Operation(
            summary = "Получить заказчика по идентификатору",
            description = "Получает информацию о заказчике по его идентификатору."
    )
    @GetMapping("/getCustomerById{customerId}")
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
            @RequestBody @Valid RegistrationCustomerDTO registrationCustomerDTO,
            @RequestParam Long customerId
    ) {
        return ResponseEntity.ok(customerService.updateCustomerById(customerId,registrationCustomerDTO));
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
