package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.RestourantMenuItemDTO;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.repositories.RestaurantMenuItemsRepository;
import ru.liga.repositories.RestaurantRepository;

@Service
@AllArgsConstructor
public class MenuItemsService {
    private final RestaurantMenuItemsRepository restaurantMenuItemsRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantMenuItem createMenuItem(Long id, RestourantMenuItemDTO item) {
        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        restaurantMenuItem.setRestaurant(restaurantRepository.findById(id).orElseThrow());
        restaurantMenuItem.setName(item.getName());
        restaurantMenuItem.setPrice(item.getPrice());
        restaurantMenuItem.setDescription(item.getDescription());
//        restaurantMenuItem.setImage(null);

      return  restaurantMenuItemsRepository.save(restaurantMenuItem);
    }
    public void deleteMenuItem(Long id) {
        restaurantMenuItemsRepository.deleteById(id);
    }
}
