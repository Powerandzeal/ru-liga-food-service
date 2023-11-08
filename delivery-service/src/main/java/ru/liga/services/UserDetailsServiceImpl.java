package ru.liga.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.liga.models.Users;

@AllArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

   private final CourierService courierService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = courierService.findByNumber(username);
        log.info(String.valueOf(users));
        log.info("Trying to authenticate user with username: {}",username );

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .roles(users.getRoleUser().name())
                .build();
        log.info("Succesful authorization"+ userDetails);
        log.info(userDetails.toString());
        return userDetails;
    }

}
