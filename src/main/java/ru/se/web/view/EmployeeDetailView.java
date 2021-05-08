package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Employee;

public class EmployeeDetailView extends ModelAndView {
    public EmployeeDetailView(){
        super("employeedetail");
    }
    public void bindEmployee(Employee employee){
        if(employee==null){
            this.bindViewName("error");
            return;
        }
        this.addObject("emp",employee);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
