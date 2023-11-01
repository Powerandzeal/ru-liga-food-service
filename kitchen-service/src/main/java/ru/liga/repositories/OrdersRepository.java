package ru.liga.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
