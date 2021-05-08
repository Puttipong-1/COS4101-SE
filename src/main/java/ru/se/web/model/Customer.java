package ru.se.web.model;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.persistence.*;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "customer",schema = "se")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    private String customerCode;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;
    private String province;
    private String district;
    private String subDistrict;
    private String zipCode;
    private boolean isActive;
    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;
    @OneToMany(mappedBy = "customer")
    private List<Log> customerLogs;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    public Customer(){}
    public Customer(long customerId){
        this.customerId=customerId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Log> getCustomerLogs() {
        return customerLogs;
    }

    public void setCustomerLogs(List<Log> customerLogs) {
        this.customerLogs = customerLogs;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFullName(){
        return this.firstname+" "+this.lastname;
    }
    public String getFullAddress(){
        return this.address+" "+this.getSubDistrict()+" "+this.getDistrict()+" "+this.getProvince()+" "+this.zipCode;
    }
}
