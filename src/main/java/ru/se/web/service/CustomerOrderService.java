package ru.se.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.model.Cart;
import ru.se.web.model.Customer;
import ru.se.web.model.Order;
import ru.se.web.model.OrderDetail;
import ru.se.web.repositories.CartRepository;
import ru.se.web.repositories.CustomerRepository;
import ru.se.web.repositories.OrderDetailRepository;
import ru.se.web.repositories.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService {
    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerOrderService.class);
    @Autowired
    public CustomerOrderService(CartRepository cartRepository,CustomerRepository customerRepository,OrderRepository orderRepository,OrderDetailRepository orderDetailRepository) {
        this.cartRepository=cartRepository;
        this.customerRepository=customerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository=orderDetailRepository;
    }
    public Customer getCustomerWithCart(long customerId){
        return customerRepository.findById(customerId).orElse(null);
    }
    public String makeOrder(Customer customer,int total_quantity,double total_price){
        String orderCode=createOrderCode(orderRepository.countAllOrderRow());
        Order order=new Order(LocalDate.now(), LocalTime.now(), 1,total_quantity,total_price,orderCode,customer);
        order=orderRepository.addOrder(order);
        orderDetailRepository.addOrderDetails(createOrderDetails(customer.getCarts(),order));
        cartRepository.deleteByCustomer(customer);
        return orderCode;
    }
    public List<Order> getAllOrderByCustomerId(long customerId){
        return orderRepository.getAllOrderByCustomerId(customerId);
    }
    public Order getCustomerOrder(String orderCode,long customerId){
        return orderRepository.getOrderById(orderCode,customerId).orElse(null);
    }
    private String createOrderCode(long count){
        return String.format("O%06d",count+1);
    }
    private List<OrderDetail> createOrderDetails(List<Cart> carts,Order order){
        List<OrderDetail> orderDetails=new ArrayList<>();
        for(Cart c:carts){
            OrderDetail orderDetail=new OrderDetail(c.getQuantity(),c.getSumPrice(),order,c.getProduct());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
