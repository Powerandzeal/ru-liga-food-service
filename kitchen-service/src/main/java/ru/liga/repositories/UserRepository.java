package ru.liga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Users;

public interface UserRepository extends JpaRepository<Users , Long> {

}
