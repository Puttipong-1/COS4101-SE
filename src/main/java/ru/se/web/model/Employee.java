package ru.se.web.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee",schema = "se")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employeeId;
    private String employeeCode;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private int role;
    @OneToMany(mappedBy = "employee")
    private List<Log> logs;

    public Employee() {
    }

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    public String getFullName(){
        return firstname+" "+lastname;
    }
}
