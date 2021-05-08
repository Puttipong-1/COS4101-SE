package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.se.web.model.Employee;
import ru.se.web.service.ManageEmployeeService;
import ru.se.web.utility.EmpSession;
import ru.se.web.view.AddEmployeeView;
import ru.se.web.view.EmployeeDetailView;
import ru.se.web.view.EmployeeListView;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class ManageEmployeeController {
    private final ManageEmployeeService manageEmployeeService;
    private static final Logger LOGGER= LoggerFactory.getLogger(ManageEmployeeController.class);
    @Autowired
    public ManageEmployeeController(ManageEmployeeService manageEmployeeService){
        this.manageEmployeeService=manageEmployeeService;
    }
    @GetMapping("/admin/employees")
    public EmployeeListView employeeList(HttpSession session){
        EmployeeListView view=new EmployeeListView();
        if(EmpSession.checkEmp(session)){
            if(EmpSession.getEmpRole(session)!=2){
                view.bindViewName("redirect:/admin");
                return view;
            }
            view.bindEmployeeList(manageEmployeeService.getAllEmployeeASC());
        }
        else view.bindViewName("redirect:/admin/login");
        return view;
    }
    @GetMapping("/admin/employees/{code}")
    public EmployeeDetailView employeeDetail(HttpSession session, @PathVariable("code") String employeeCode){
        EmployeeDetailView view=new EmployeeDetailView();
        if(EmpSession.checkEmp(session)){
            if(EmpSession.getEmpRole(session)!=2){
                view.bindViewName("redirect:/admin");
                return view;
            }
            view.bindEmployee(manageEmployeeService.getEmployeeByCode(employeeCode));
        }
        else view.bindViewName("redirect:/admin/login");

        return view;
    }
    @GetMapping("/admin/employees/add")
    public AddEmployeeView addEmployee(HttpSession session){
        AddEmployeeView view=new AddEmployeeView();
        if(EmpSession.checkEmp(session)){
            if(EmpSession.getEmpRole(session)!=2){
                view.bindViewName("redirect:/admin");
                return view;
            }
            view.bindEmployee(new Employee());
        }
        else view.bindViewName("redirect:/admin/login");
        return view;
    }
    @PostMapping("/admin/employees/add")
    public AddEmployeeView onAddEmployee(HttpSession session,@ModelAttribute("emp") Employee employee){
        AddEmployeeView view=new AddEmployeeView();
        if(manageEmployeeService.existByEmail(employee.getEmail())){
            view.bindMsg("อีเมลถูกใช้ไปแล้ว");
            return view;
        }
        manageEmployeeService.saveEmployee(employee);
        view.bindViewName("redirect:/admin/employees");
        return view;
    }
}
