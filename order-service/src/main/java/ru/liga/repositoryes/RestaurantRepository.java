package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
