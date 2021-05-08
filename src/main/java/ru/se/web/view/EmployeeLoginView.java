package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;

public class EmployeeLoginView extends ModelAndView {
    public EmployeeLoginView(){
        super("employeelogin");
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
