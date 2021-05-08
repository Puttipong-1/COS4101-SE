package ru.se.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.se.web.service.AdminService;
import ru.se.web.utility.EmpSession;
import ru.se.web.view.AdminView;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/admin")
    public AdminView admin(HttpSession session){
        AdminView view=new AdminView();
        if(!EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin/login");
            return view;
        }
        view.bindStats(adminService.getStats());
        return view;
    }
}
