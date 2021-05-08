package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.se.web.model.Customer;
import ru.se.web.service.EmailService;
import ru.se.web.service.RegisterService;
import ru.se.web.utility.CustomerSession;
import ru.se.web.view.ActivateView;
import ru.se.web.view.RegisterView;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    private final RegisterService registerService;
    private final EmailService emailService;
    private final Argon2PasswordEncoder argon2;
    private static final Logger LOGGER= LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    public RegisterController(RegisterService registerService,EmailService emailService) {
        this.registerService = registerService;
        this.emailService = emailService;
        this.argon2=new Argon2PasswordEncoder(16,32,1,1024,5);
    }
    @GetMapping("/register")
    public RegisterView register(HttpSession session){
        RegisterView view=new RegisterView();
        if(CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/");
            return view;
        }
        view.bindCustomer(new Customer());
        return view;
    }
    @PostMapping("/register")
    public RegisterView onRegister(HttpSession session,
                                   RedirectAttributes redirect,
                                   @ModelAttribute("customer") Customer customer){
        RegisterView view=new RegisterView();
        if(registerService.existByEmail(customer.getEmail())){
            view.bindCustomer(customer);
            view.bindMsg("อีเมลถูกใช้ไปแล้ว");
            return view;
        }
        emailService.sendActivationEmail(customer.getEmail());
        registerService.register(customer);
        redirect.addFlashAttribute("msg", "สมัครสมาชิกสำเร็จ กรุณายืนยันการสมัครสมาชิก");
        view.bindViewName("redirect:/login");
        return view;
    }
    @GetMapping("/activate/{p1}")
    public ActivateView activateEmail(HttpSession session,@PathVariable("p1") String p1){
        ActivateView view=new ActivateView();
        if(CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/");
            return view;
        }
        switch(registerService.validateLink(emailService.decode(p1))){
            case -1: {
                view.bindMsg("ลิงค์ยืนยันการสมัครสมาชิกไม่ถูกต้อง");
                break;
            }
            case 0: {
                view.bindMsg("ยืนยันการสมัครสมาชิกสำเร็จ");
                break;
            }
            case 1: {
                view.bindMsg("บัญชีนี้ถูกยืนยันการสมัครสมาชิกไปแล้ว");
                break;
            }
        }
        return view;
    }
}
