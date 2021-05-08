package ru.se.web.model;

import javax.persistence.*;

@Entity
@Table(name = "cart",schema = "se")
public class Cart {
    @EmbeddedId
    private CartId cartId;
    private int quantity;
    private double sumPrice;
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    @ManyToOne
    private Customer customer;
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;
    public Cart() {
    }

    public Cart(Customer customer,Product product,int quantity) {
        this.customer=customer;
        this.product=product;
        this.cartId=new CartId(customer.getCustomerId(),product.getProductId());
        this.quantity = quantity;
        this.sumPrice = Math.round((quantity*product.getPrice())*100.0)/100.0;
    }

    public CartId getCartId() {
        return cartId;
    }

    public void setCartId(CartId cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
