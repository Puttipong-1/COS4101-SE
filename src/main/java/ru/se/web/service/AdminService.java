package ru.se.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.se.web.repositories.OrderRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    private final OrderRepository orderRepository;
    private static final Logger logger= LoggerFactory.getLogger(AdminService.class);
    @Autowired
    public AdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Map<String,Object> getStats(){
        Map<String,Object> map=new HashMap<>();
        LocalDate td= LocalDate.now();
        map.put("today1",orderRepository.countByStatusToday(1,td));
        map.put("today2",orderRepository.countByStatusToday(2,td));
        map.put("today3",orderRepository.countByStatusToday(3,td));
        map.put("today4",orderRepository.countByStatusToday(4,td));
        map.put("all1",orderRepository.countByStatus(1));
        map.put("all2",orderRepository.countByStatus(2));
        map.put("all3",orderRepository.countByStatus(3));
        map.put("all4",orderRepository.countByStatus(4));
        return map;
    }

}
