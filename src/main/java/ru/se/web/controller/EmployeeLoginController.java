package ru.se.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.se.web.model.Employee;
import ru.se.web.service.EmployeeLoginService;
import ru.se.web.utility.EmpSession;
import ru.se.web.view.EmployeeLoginView;
import javax.servlet.http.HttpSession;

@Controller
public class EmployeeLoginController {
    private final EmployeeLoginService employeeLoginService;
    private final Argon2PasswordEncoder argon2;
    @Autowired
    public EmployeeLoginController(EmployeeLoginService employeeLoginService) {
        this.employeeLoginService = employeeLoginService;
        this.argon2=new Argon2PasswordEncoder(16,32,1,1024,5);
    }
    @GetMapping("/admin/login")
    public EmployeeLoginView login(HttpSession session){
        EmployeeLoginView view=new EmployeeLoginView();
        if(EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin");
            return view;
        }
        return view;
    }
    @PostMapping("/admin/login")
    public String onLogin(HttpSession session,
                          RedirectAttributes redirect,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password){
        Employee employee=employeeLoginService.findEmployeeByEmail(email);
        if(employee==null){
            redirect.addFlashAttribute("msg","ไม่พบอีเมลในระบบ");
            return "redirect:/admin/login";
        }
        if(!argon2.matches(password,employee.getPassword())){
            redirect.addFlashAttribute("msg","รหัสผ่านไม่ถูกต้อง");
            return "redirect:/admin/login";
        }
        EmpSession.saveEmp(session,employee);
        employeeLoginService.saveLog(employee,"เข้าสู่ระบบ");
        return "redirect:/admin";
    }
    @PostMapping("/admin/logout")
    public String onLogout(HttpSession session){
        if(EmpSession.checkEmp(session)){
            employeeLoginService.saveLog(new Employee(EmpSession.getEmpId(session)),"ออกจากระบบ");
            EmpSession.EmpLogout(session);
        }
        return "redirect:/admin/login";
    }
}
