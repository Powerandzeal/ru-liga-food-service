package ru.liga.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.liga.models.Restaurant;
import ru.liga.models.Users;
import ru.liga.repositories.RestaurantRepository;
import ru.liga.repositories.UserRepository;

@AllArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

   private final RestaurantRepository restaurantRepository;
   private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Restaurant restaurant = restaurantRepository.findByNameRestaurant(username);
        log.info(String.valueOf(restaurant));
        log.info("Trying to authenticate user with username: {}",username );

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(restaurant.getNameRestaurant())
                .password(restaurant.getUsers().getPassword())
                .roles(restaurant.getUsers().getRoleUser().name())
                .build();
        System.out.println("Была авторизация");
        log.info(userDetails.toString());
        return userDetails;
    }

}
