package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.model.Employee;
import ru.se.web.repositories.EmployeeRepository;

@Service
public class EmployeeProfileService {
    public final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeProfileService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    public Employee getEmployeeById(long employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }
}
