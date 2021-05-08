package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Customer;

public class CustomerProfileView extends ModelAndView {
    public CustomerProfileView(){
        super("customerprofile");
    }
    public void bindCustomer(Customer customer){
        this.addObject("customer",customer);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
