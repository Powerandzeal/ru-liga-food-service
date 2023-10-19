package ru.liga.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.models.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
}
