package ru.se.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product",schema = "se")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;
    private String productCode;
    private String name;
    private String description;
    private double price;
    private String objectKey;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
    public Product(){}
    public Product(long productId){
        this.productId=productId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
