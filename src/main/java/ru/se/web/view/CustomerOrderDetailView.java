package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Customer;
import ru.se.web.model.Order;
import ru.se.web.model.OrderDetail;

public class CustomerOrderDetailView extends ModelAndView {
    public CustomerOrderDetailView(){
        super("customerorderdetail");
    }
    public void bindOrder(Order order){
        if(order==null){
            this.bindViewName("error");
            return;
        }
        this.addObject("order",order);
        this.addObject("delivery",50.0);
        this.addObject("totalOrder",order.getTotalPrice()+50.0);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
