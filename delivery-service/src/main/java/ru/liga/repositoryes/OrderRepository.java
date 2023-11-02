package ru.liga.repositoryes;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.Enum.DeliveryStatusOrder;
import ru.liga.models.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> getOrdersByOrderStatus(DeliveryStatusOrder statusOrder);
    List<Orders> getOrdersByCourier_Id(Long id);
}
