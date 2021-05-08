package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.se.web.model.Customer;
import ru.se.web.service.CustomerProfileService;
import ru.se.web.utility.CustomerSession;
import ru.se.web.view.CustomerProfileView;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;
    private final static Logger LOGGER= LoggerFactory.getLogger(CustomerProfileController.class);
    @Autowired
    public CustomerProfileController(CustomerProfileService customerProfileService){
        this.customerProfileService=customerProfileService;
    }
    @GetMapping("/profile")
    public CustomerProfileView profile(HttpSession session){
        CustomerProfileView view=new CustomerProfileView();
        if(!CustomerSession.checkCustomer(session)){
            view.setViewName("redirect:/login");
            return view;
        }
        long id=CustomerSession.getCustomerId(session);
        view.bindCustomer(customerProfileService.findCustomerById(id));
        return view;
    }

}
