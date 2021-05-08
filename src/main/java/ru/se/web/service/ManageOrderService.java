package ru.se.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.model.Company;
import ru.se.web.model.Order;
import ru.se.web.repositories.CompanyRepository;
import ru.se.web.repositories.OrderRepository;

import java.util.List;

@Service
public class ManageOrderService {
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
    @Autowired
    public ManageOrderService(OrderRepository orderRepository, CompanyRepository companyRepository){
        this.orderRepository=orderRepository;
        this.companyRepository=companyRepository;
    }
    public List<Order> getOrderListByStatusASC(int status){
        return orderRepository.findAllByStatusOrderByOrderIdDesc(status);

    }
    public Order getOrderByCode(String orderCode){
        return orderRepository.findFirstByOrderCode(orderCode);
    }
    public void updateOrderStatus(long orderId,int status){
        orderRepository.updateOrderStatus(orderId,status);
    }
    public Company getCompany() {
        return companyRepository.findTopByOrderByCompanyIdDesc();
    }
}
