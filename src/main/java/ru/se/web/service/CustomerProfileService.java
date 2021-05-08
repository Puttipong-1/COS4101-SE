package ru.se.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.se.web.model.Customer;
import ru.se.web.repositories.CustomerRepository;
@Service
public class CustomerProfileService {
    private CustomerRepository customerRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerProfileService.class);
    @Autowired
    public CustomerProfileService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    public Customer findCustomerById(long id){
        return customerRepository.findById(id).orElse(null);
    }
}
