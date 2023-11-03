package ru.liga.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.DTO.RegistrationCustomerDTO;
import ru.liga.Exceptions.CustomerNotFoundException;
import ru.liga.models.Customers;
import ru.liga.repositoryes.CustomerRepository;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
//    public Customers createCustomer(RegistrationCustomerDTO registrationCustomerDTO) {
//        Customers customers = new Customers();
//        customers.setAddress(registrationCustomerDTO.getAddress());
//        customers.setPhone(registrationCustomerDTO.getPhone());
//        customers.setEmail(registrationCustomerDTO.getEmail());
//
//        return customerRepository.save(customers);
//    }
//    public Customers getCustomerById (Long idCustomer) {
//        return customerRepository.findById(idCustomer).orElseThrow();
//    }
//
//    public Customers updateCustomerById(Long id) {
//        return null;
//    }
//    public void deleteCustomerById(Long id) {
//        customerRepository.deleteById(id);
//    }
//    public List<Orders> getAllOwnOrders(Long customerId){
//        return null;
//    }
    /**
     * Создает нового клиента на основе данных из DTO и сохраняет его в репозитории.
     *
     * @param registrationCustomerDTO Данные для создания нового клиента.
     * @return Созданный клиент.
     */
    public Customers createCustomer(RegistrationCustomerDTO registrationCustomerDTO) {
        Customers customers = new Customers();
        customers.setAddress(registrationCustomerDTO.getAddress());
        customers.setPhone(registrationCustomerDTO.getPhone());
        customers.setEmail(registrationCustomerDTO.getEmail());

        Customers savedCustomer = customerRepository.save(customers);
        log.info("Created customer with ID: {}", savedCustomer.getId());
        return savedCustomer;
    }

    /**
     * Получает клиента по его идентификатору.
     *
     * @param idCustomer Идентификатор клиента.
     * @return Клиент с указанным идентификатором.
     * @throws CustomerNotFoundException если клиент с указанным идентификатором не найден.
     */
    public Customers getCustomerById(Long idCustomer) { //Переделать на authethication
        Customers customer = customerRepository.findById(idCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + idCustomer));
        log.info("Retrieved customer by ID: {}", idCustomer);
        return customer;
    }

    /**
     * Обновляет информацию о клиенте по его идентификатору.
     *
     * @param customerId Идентификатор клиента, которого нужно обновить.
     * @return Обновленный клиент.
     */
    public Customers updateCustomerById(Long customerId,RegistrationCustomerDTO registrationCustomerDTO) {

        Customers updatedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
        updatedCustomer.setEmail(registrationCustomerDTO.getEmail());
        updatedCustomer.setPhone(registrationCustomerDTO.getPhone());
        updatedCustomer.setAddress(registrationCustomerDTO.getAddress());
        customerRepository.save(updatedCustomer);
        log.info("Updated customer by ID: {}", customerId);
        return updatedCustomer;
    }

    /**
     * Удаляет клиента по его идентификатору.
     *
     * @param id Идентификатор клиента, которого нужно удалить.
     */
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        log.info("Deleted customer by ID: {}", id);
    }

    /**
     * Получает список заказов, принадлежащих клиенту с указанным идентификатором.
     *
     * @param customerId Идентификатор клиента.
     * @return Список заказов клиента.
     */
//    public List<Orders> getAllOwnOrders(Long customerId) {
//        // Ваш код получения заказов
//        List<Orders> orders = /* Ваш код получения заказов */;
//        log.info("Retrieved {} orders for customer with ID: {}", orders.size(), customerId);
//        return orders;
//    }
}
//}
