package ru.se.web.model;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDetailId implements Serializable {

    private Long orderId;
    private Long productId;
    public OrderDetailId(){}
    public OrderDetailId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
