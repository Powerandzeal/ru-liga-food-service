package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Courier;
import ru.liga.models.Users;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {

//    Users find(String phoneNumber);
    Optional<Courier> findByPhonenumber(String phoneNumber);
}
