package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Employee;
import java.util.List;
public class EmployeeListView extends ModelAndView {
    public EmployeeListView(){
        super("employeelist");
    }
    public void bindEmployeeList(List<Employee> employeeList){
        this.addObject("employees",employeeList);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
