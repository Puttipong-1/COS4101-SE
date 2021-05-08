package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.model.Product;
import ru.se.web.repositories.CartRepository;
import ru.se.web.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HomeService {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    @Autowired
    public HomeService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }
    public List<Product> getAllProductDesc(){
        return productRepository.getAllProductDesc();
    }
}
