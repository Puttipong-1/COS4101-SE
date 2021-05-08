package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.se.web.model.OrderDetail;
import java.util.List;
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    default void addOrderDetails(List<OrderDetail> orderDetails){
        saveAll(orderDetails);
    }
}
