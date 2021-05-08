package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Company;
import ru.se.web.model.Order;

public class PrintOrderView extends ModelAndView {
    public PrintOrderView(){
        super("printorder");
    }
    public void bindOrderAndCompany(Order order, Company company){
        if(order==null){
            this.bindViewName("error");
        }
        else{
            Double deliveryCost=50.00;
            if(order.getStatus()==1||order.getStatus()==4){
                this.bindViewName("error");
            }
            this.addObject("order",order);
            this.addObject("company",company);
            this.addObject("delivery",deliveryCost);
            this.addObject("orderSum",order.getTotalPrice()+deliveryCost);
        }
    }
    public void bindViewName(String name){
        this.setViewName(name);
    }
}
