package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.se.web.model.Customer;
import ru.se.web.repositories.CustomerRepository;

@Service
public class RegisterService {
    private final CustomerRepository customerRepository;
    private final Argon2PasswordEncoder argon2;
    @Autowired
    public RegisterService(CustomerRepository customerRepository,EmailService emailService) {
        this.customerRepository = customerRepository;
        this.argon2=new Argon2PasswordEncoder(16,32,1,1024,5);
    }
    public boolean existByEmail(String email){
        return customerRepository.existsByEmail(email);
    }
    public void register(Customer customer){
        String customerCode=createCustomerCode(customerRepository.countAllCustomerRow());
        customer.setPassword(argon2.encode(customer.getPassword()));
        customer.setActive(false);
        customer.setCustomerCode(customerCode);
        customerRepository.addCustomer(customer);
    }
    private String createCustomerCode(long count){
        return String.format("C%06d",count+1);
    }
    public int validateLink(String email){
        int flag=-1;
        Customer customer=customerRepository.findFirstByEmail(email);
        if(customer!=null){
            if(!customer.getActive()) {
                flag = 0;
                customerRepository.updateActivateByEmail(email);
            }
            else flag=1;
        }
        return flag;
    }
}
