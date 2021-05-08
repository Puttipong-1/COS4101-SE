package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.se.web.model.Customer;
import ru.se.web.model.Order;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT o FROM Order o WHERE o.customer.customerId=:id ORDER BY o.orderId DESC")
    List<Order> getAllOrderByCustomerId(@Param("id") long id);

    @Query(value = "SELECT DISTINCT o FROM Order o WHERE o.orderCode=:orCode AND o.customer.customerId=:cusId")
    Optional<Order> getOrderById(@Param("orCode") String orCode,@Param("cusId") long cusId);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status=:status AND o.date=:date")
    int countByStatusToday(@Param("status") int status,@Param("date") LocalDate date);

    int countByStatus(int status);

    List<Order> findAllByStatusOrderByOrderIdDesc(int status);

    Order findFirstByOrderCode(String orderCode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Order o SET o.status=:status WHERE o.orderId=:id")
    void updateOrderStatus(long id,int status);
    Order findTopByOrderByOrderIdDesc();
    default Order addOrder(Order order){
        return saveAndFlush(order);
    }
    default long countAllOrderRow(){
        return count();
    }
}
