package ru.se.web.model;



import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name="order",schema = "se")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderId;
    private String orderCode;
    private LocalDate date;
    private LocalTime time;
    private int status;
    private int totalQuantity;
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;

    public Order() { }
    public Order(Long orderId) {
        this.orderId = orderId;
    }
    public Order(LocalDate date, LocalTime time, int status, int totalQuantity, double totalPrice, String orderCode,Customer customer) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.orderCode=orderCode;
        this.customer = customer;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    public String formatDateTime(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime= LocalDateTime.of(this.date,this.time);
        return dateTime.format(formatter);
    }

}
