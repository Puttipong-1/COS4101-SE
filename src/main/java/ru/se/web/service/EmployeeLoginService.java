package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.se.web.model.Employee;
import ru.se.web.model.Log;
import ru.se.web.repositories.EmployeeRepository;
import ru.se.web.repositories.LogRepository;
import ru.se.web.utility.EmpSession;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeLoginService {
    private final EmployeeRepository employeeRepository;
    private final LogRepository logRepository;
    @Autowired
    public EmployeeLoginService(EmployeeRepository employeeRepository, LogRepository logRepository) {
        this.employeeRepository = employeeRepository;
        this.logRepository = logRepository;
    }
    public Employee findEmployeeByEmail(String email){
        return employeeRepository.findFirstByEmail(email);
    }
    public void saveLog(Employee employee,String action){
        logRepository.addLog(new Log(LocalDateTime.now(),action,employee));
    }
    public void save(String password){
        Employee employee=new Employee();
        employee.setEmployeeCode(String.format("E%06d",employeeRepository.count()+1));
        employee.setFirstname("test");
        employee.setLastname("test");
        employee.setEmail("manager@se.com");
        employee.setPhoneNumber("0801465858");
        employee.setPassword(password);
        employee.setRole(2);
        employeeRepository.save(employee);
    }

}
