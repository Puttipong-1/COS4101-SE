package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.se.web.model.Cart;
import ru.se.web.model.Customer;
import ru.se.web.model.Product;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "SELECT c FROM Cart c WHERE c.cartId.customerId=:id")
    List<Cart> getCustomerCart(@Param("id") long id);
    @Transactional
    @Modifying
    void deleteByCustomerAndProduct(Customer customer,Product product);
    @Transactional
    @Modifying
    void deleteByCustomer(Customer customer);

    default void addCart(Cart cart){
        save(cart);
    }
}
