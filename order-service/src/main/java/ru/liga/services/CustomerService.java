package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.RegistrationCustomerDTO;
import ru.liga.models.Customers;
import ru.liga.repositoryes.CustomerRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public Customers createCustomer(RegistrationCustomerDTO registrationCustomerDTO) {
        Customers customers = new Customers();
        customers.setAddress(registrationCustomerDTO.getAddress());
        customers.setPhone(registrationCustomerDTO.getPhone());
        customers.setEmail(registrationCustomerDTO.getEmail());

        return customerRepository.save(customers);
    }
    public Optional<Customers> getCustomerById (Long idCustomer) {
        return customerRepository.findById(idCustomer);
    }

    public Customers updateCustomerById(Long id) {
        return null;
    }
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

    }
}