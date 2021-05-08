package ru.se.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.se.web.service.EmployeeProfileService;
import ru.se.web.utility.EmpSession;
import ru.se.web.view.EmployeeDetailView;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeProfileController {
    private final EmployeeProfileService employeeProfileService;
    @Autowired
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }
    @GetMapping("/admin/profile")
    public EmployeeDetailView profile(HttpSession session){
        EmployeeDetailView view=new EmployeeDetailView();
        if(!EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin/login");
            return view;
        }
        long employeeId=EmpSession.getEmpId(session);
        view.bindEmployee(employeeProfileService.getEmployeeById(employeeId));
        return view;
    }
}
