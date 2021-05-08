package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;

public class CustomerLoginView extends ModelAndView {
    public CustomerLoginView(){
        super("customerlogin");
    }
    public void bindView(String view){
        this.setViewName(view);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
