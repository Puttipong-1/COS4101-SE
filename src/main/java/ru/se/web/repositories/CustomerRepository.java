package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.se.web.model.Customer;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
    Customer findFirstByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer c SET c.isActive=true WHERE c.email=:email")
    void updateActivateByEmail(@Param("email") String email);
    @Query(value = "SELECT c.isActive FROM Customer c WHERE c.email=:email")
    boolean checkCustomerActive(@Param("email") String email);
    Customer getTopByOrderByCustomerIdDesc();

    default void addCustomer(Customer customer){
        save(customer);
    }
    default long countAllCustomerRow(){
        return count();
    }
}
