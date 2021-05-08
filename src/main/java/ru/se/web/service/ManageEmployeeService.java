package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.se.web.model.Employee;
import ru.se.web.repositories.EmployeeRepository;
import java.util.List;
@Service
public class ManageEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Argon2PasswordEncoder argon2;
    @Autowired
    public ManageEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.argon2=new Argon2PasswordEncoder(16,32,1,1024,5);
    }
    public List<Employee> getAllEmployeeASC(){
        return employeeRepository.getAllEmployeeASC();
    }
    public Employee getEmployeeByCode(String employeeCode){
        return employeeRepository.findFirstByEmployeeCode(employeeCode);
    }
    public boolean existByEmail(String email){
        return employeeRepository.existsByEmail(email);
    }
    public void saveEmployee(Employee employee){
        String employeeCode=createEmployeeCode(employeeRepository.countAllEmployeeRow());
        employee.setPassword(argon2.encode(employee.getPassword()));
        employee.setEmployeeCode(employeeCode);
        employee.setRole(1);
        employeeRepository.save(employee);
    }
    private String createEmployeeCode(long count){
        return String.format("E%06d",count+1);
    }
}
