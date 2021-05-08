package ru.se.web.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartId implements Serializable {
    private Long customerId;
    private Long productId;
    public CartId(){}
    public CartId(Long customerId,Long productId){
        this.customerId=customerId;
        this.productId=productId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartId)) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(customerId, cartId.customerId) &&
                Objects.equals(productId, cartId.productId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }
}
