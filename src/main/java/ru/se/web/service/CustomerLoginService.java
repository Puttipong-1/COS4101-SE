package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.se.web.model.Customer;
import ru.se.web.repositories.CustomerRepository;
import ru.se.web.repositories.LogRepository;
import ru.se.web.model.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
@Service
public class CustomerLoginService {
    private final CustomerRepository customerRepository;
    private final LogRepository logRepository;
    @Autowired
    public CustomerLoginService(CustomerRepository customerRepository, LogRepository logRepository) {
        this.customerRepository = customerRepository;
        this.logRepository = logRepository;
    }
    public Customer findCustomerByEmail(String email){
        return customerRepository.findFirstByEmail(email);
    }
    public void saveLog(Customer customer,String action){
        logRepository.addLog(new Log(LocalDateTime.now(),action,customer));
    }
}
