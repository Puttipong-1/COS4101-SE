package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Employee;

public class AddEmployeeView extends ModelAndView {
    public AddEmployeeView(){
        super("addemployee");
    }
    public void bindEmployee(Employee employee){
        this.addObject("emp",employee);
    }
    public void bindMsg(String msg){
        this.addObject("msg",msg);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }

}
