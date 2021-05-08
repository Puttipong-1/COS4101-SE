package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.se.web.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p FROM Product p ORDER BY p.productId DESC")
    List<Product> getAllProductDesc();
    @Query(value = "SELECT p FROM Product p ORDER BY p.productId ASC")
    List<Product> getAllProductAsc();
    Product findFirstByProductCode(String code);
    Product getTopByOrderByProductIdDesc();
    default void addProduct(Product product){
        save(product);
    }
}
