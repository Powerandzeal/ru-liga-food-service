package ru.liga.repositoryes;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Users;

public interface UserRepository extends JpaRepository<Users , Long> {
    Users findByUsername(String phoneNumber);
}
