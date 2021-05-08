package ru.se.web.model;

import javax.persistence.*;

@Entity
@Table(name = "orderDetail",schema = "se")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId orderDetailId;
    private int quantity;
    private Double sumPrice;
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    @ManyToOne
    private Order order;

    @MapsId("productId")
    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;
    public OrderDetail(){ }

    public OrderDetail(int quantity, double sumPrice, Order order, Product product) {
        this.quantity = quantity;
        this.sumPrice = sumPrice;
        this.order = order;
        this.product = product;
        this.orderDetailId=new OrderDetailId(order.getOrderId(), product.getProductId());
    }

    public OrderDetailId getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(OrderDetailId orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
