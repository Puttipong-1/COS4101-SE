package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Customer;

public class RegisterView extends ModelAndView {
    public RegisterView() {
        super("register");
    }
    public void bindCustomer(Customer customer){
        this.addObject("customer",customer);
    }
    public void bindMsg(String msg){
        this.addObject("msg",msg);
    }
    public void bindViewName(String name){
        this.setViewName(name);
    }
}
