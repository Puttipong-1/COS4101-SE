package ru.se.web.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "log",schema = "se")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long logId;
    private LocalDateTime dateTime;
    private String action;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    public Log(){}
    public Log(LocalDateTime dateTime, String action, Customer customer) {
        this.dateTime = dateTime;
        this.action = action;
        this.customer = customer;
    }
    public Log(LocalDateTime dateTime, String action, Employee employee) {
        this.dateTime = dateTime;
        this.action = action;
        this.employee = employee;
    }
}
