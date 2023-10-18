package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Courier;

public interface CourierRepository extends JpaRepository<Courier,Long> {

}
