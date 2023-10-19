package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
