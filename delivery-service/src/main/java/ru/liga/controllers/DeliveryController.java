package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.liga.DTO.CreateCourierDTO;
import ru.liga.DTO.NotificationOrdersForCourier;
import ru.liga.DTO.UpdateCourierDTO;
import ru.liga.Exceptions.NotFoundCourierException;
import ru.liga.models.Courier;
import ru.liga.models.Orders;
import ru.liga.services.CourierService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/courier")
@Slf4j
public class DeliveryController {

    private final CourierService courierService;


    /**
     * Создает нового курьера.
     *
     * @param courier Данные о новом курьере.
     * @return Созданный курьер.
     */
    @Operation(
            summary = "Создать курьера",
            description = "Создает нового курьера."
    )
    @PermitAll
    @PostMapping("/create-courier")
    @PreAuthorize("hasRole('ROLE_COURIER')")
    public ResponseEntity<Courier> createCourier(
            @RequestBody @Valid CreateCourierDTO courier
    ) {
        Courier createdCourier = courierService.createCourier(courier);
        log.info("Создание курьера");
        return ResponseEntity.ok(createdCourier);
    }


    /**
     * Получает все заказы курьера за все время .
     *
     * @param courierId        Идентификатор курьера.
     * @return возвращает заказы курьера .
     */
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @GetMapping("/getOrdersByCoureir{courierId}")
    public ResponseEntity<List<Orders>> getAllOrdersCourier(
            @PathVariable Long courierId
    ) {
        List<Orders> orders = courierService.getAllCourierOrders(courierId);
        return ResponseEntity.ok(orders);
    }


    /**
     * Обновляет информацию о курьере.
     *
     * @param courierId        Идентификатор курьера.
     * @param updateCourierDTO Обновленные данные о курьере.
     * @return Ответ об обновлении курьера.
     */
    @Operation(
            summary = "Обновить курьера",
            description = "Обновляет информацию о курьере."
    )
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PutMapping("/{courierId}")
    public ResponseEntity<String> updateCourier(
            @PathVariable Long courierId,
            @RequestBody @Valid UpdateCourierDTO updateCourierDTO
    ) {
        log.info("Запрос на обновление курьера по идентификатору: {}", courierId);
        courierService.updateCourierById(courierId, updateCourierDTO);
        return ResponseEntity.ok("Курьер обновлен");
    }

    /**
     * Удаляет курьера по его идентификатору.
     *
     * @param courierId Идентификатор курьера.
     * @return Ответ об удалении курьера.
     */
    @Operation(
            summary = "Удалить курьера",
            description = "Удаляет курьера по его идентификатору."
    )
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @DeleteMapping("/{courierId}")
    public ResponseEntity<String> deleteCourier(
            @PathVariable @Valid Long courierId
    ) {
        log.info("Запрос на удаление курьера по идентификатору: {}", courierId);
        courierService.deleteCourierById(courierId);
        return ResponseEntity.ok("Курьер удален");
    }
}
