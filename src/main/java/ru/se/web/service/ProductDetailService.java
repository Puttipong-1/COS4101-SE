package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.model.Product;
import ru.se.web.repositories.ProductRepository;

@Service
public class ProductDetailService {
    private ProductRepository productRepository;
    @Autowired
    public ProductDetailService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public Product getProductByProductCode(String productCode){
        return productRepository.findFirstByProductCode(productCode);
    }
}
