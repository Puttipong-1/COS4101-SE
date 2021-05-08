package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.se.web.model.Customer;
import ru.se.web.service.CustomerLoginService;
import ru.se.web.service.EmailService;
import ru.se.web.utility.CustomerSession;
import ru.se.web.view.CustomerLoginView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;
@Controller
public class CustomerLoginController {
    private final CustomerLoginService customerLoginService;
    private final EmailService emailService;
    private final Argon2PasswordEncoder argon2;
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerLoginController.class);
    @Autowired
    public CustomerLoginController(CustomerLoginService customerLoginService,EmailService emailService) {
        this.customerLoginService=customerLoginService;
        this.emailService=emailService;
        this.argon2=new Argon2PasswordEncoder(16,32,1,1024,5);
    }
    @GetMapping("/login")
    public CustomerLoginView login(HttpSession session){
        CustomerLoginView view=new CustomerLoginView();
        if(CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/");
        }
        return view;
    }
    @PostMapping("/login")
    public String onLogin(HttpSession session,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          RedirectAttributes redirect){
        Customer customer=customerLoginService.findCustomerByEmail(email);
        if(customer==null){
            redirect.addFlashAttribute("msg","ไม่พบบัญชีผู้ใช้ในระบบ");
            return "redirect:/login";
        }
        if(!argon2.matches(password,customer.getPassword())){
            emailService.sendActivationEmail(email);
            redirect.addFlashAttribute("msg","รหัสผ่านไม่ถูกต้อง");
            return "redirect:/login";
        }
        if(!customer.getActive()){
            emailService.sendActivationEmail(email);
            redirect.addFlashAttribute("msg","บัญชีนี้ยังไม่ยืนยันการสมัครสมาชิก กรุณายืนยันการสมัครสมาชิก");
            return "redirect:/login";
        }
        customerLoginService.saveLog(customer,"เข้าสู่ระบบ");
        CustomerSession.saveCustomer(session,customer);
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String onLogout(HttpSession session){
        if(CustomerSession.checkCustomer(session)){
            customerLoginService.saveLog(new Customer(CustomerSession.getCustomerId(session)),"ออกจากระบบ");
            CustomerSession.customerLogout(session);
        }
        return "redirect:/";
    }
}
