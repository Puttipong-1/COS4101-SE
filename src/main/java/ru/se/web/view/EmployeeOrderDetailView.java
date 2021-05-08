package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Order;

public class EmployeeOrderDetailView extends ModelAndView {
    public EmployeeOrderDetailView(){
        super("employeeorderdetail");
    }
    public void bindOrder(Order order){
        if(order==null){
            this.bindViewName("error");
            return;
        }
        this.addObject("delivery",50.0);
        this.addObject("order",order);
        this.addObject("totalOrder",order.getTotalPrice()+50.0);
    }
    public void bindViewName(String name){
        this.bindViewName(name);
    }
}
