package ru.se.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.model.*;
import ru.se.web.repositories.CartRepository;
import ru.se.web.repositories.CustomerRepository;
import ru.se.web.repositories.OrderDetailRepository;
import ru.se.web.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(CartService.class);
    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }
    public void addCart(long customerId,Product product,int quantity){
        Customer customer=new Customer(customerId);
        Cart cart=new Cart(customer,product,quantity);
        cartRepository.addCart(cart);
    }
    public void removeCart(long customerId,long productId){
        Customer customer=new Customer(customerId);
        Product product=new Product(productId);
        cartRepository.deleteByCustomerAndProduct(customer,product);
    }
}
