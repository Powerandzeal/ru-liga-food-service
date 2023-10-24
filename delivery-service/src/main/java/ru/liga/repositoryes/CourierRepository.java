package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Courier;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {


}
