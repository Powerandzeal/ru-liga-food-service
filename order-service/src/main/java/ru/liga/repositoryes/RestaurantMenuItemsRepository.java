package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.RestaurantMenuItem;

@Repository
public interface RestaurantMenuItemsRepository extends JpaRepository<RestaurantMenuItem,Long> {
}
