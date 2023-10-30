package ru.liga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.RestaurantMenuItem;

import java.util.List;

@Repository
public interface RestaurantMenuItemsRepository extends JpaRepository<RestaurantMenuItem,Long> {
    List<RestaurantMenuItem> findByRestaurantId(Long id);
}
