package ru.se.web.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Order;

import java.util.List;

public class CustomerOrderView extends ModelAndView {
    private final static Logger LOGGER= LoggerFactory.getLogger(CustomerOrderView.class);
    public CustomerOrderView(){
        super("customerorder");
    }
    public void bindOrderList(List<Order> orders){
        this.addObject("orders",orders);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
