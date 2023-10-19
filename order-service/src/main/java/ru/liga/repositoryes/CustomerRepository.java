package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Customers;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
    Optional<Customers> findById(Long id);
}
