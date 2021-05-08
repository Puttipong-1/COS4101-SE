package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.se.web.model.Cart;
import ru.se.web.model.Customer;
import ru.se.web.service.CustomerOrderService;
import ru.se.web.utility.CustomerSession;
import ru.se.web.view.CartView;
import ru.se.web.view.CustomerOrderDetailView;
import ru.se.web.view.CustomerOrderView;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerOrderController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerOrderController.class);
    private CustomerOrderService customerOrderService;
    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService){
        this.customerOrderService=customerOrderService;
    }
    @GetMapping("/makeorder")
    public CartView makeOrder(HttpSession session){
        CartView view=new CartView();
        if(!CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/login");
            return view;
        }
        Customer customer=customerOrderService.getCustomerWithCart(CustomerSession.getCustomerId(session));
        view.bindCustomerWithCart(customer);
        return view;
    }
    @PostMapping("/makeorder")
    public String onMakeOrder(@ModelAttribute("customer") Customer customer,
                                               @RequestParam("total_quan") int total_quantity,
                                               @RequestParam("total_price") Double total_price){
        String orderCode=customerOrderService.makeOrder(customer,total_quantity,total_price);
        return "redirect:/orders/"+orderCode;
    }
    @GetMapping("/orders")
    public CustomerOrderView orderList(HttpSession session){
        CustomerOrderView view=new CustomerOrderView();
        if(!CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/login");
            return view;
        }
        long customerId=CustomerSession.getCustomerId(session);
        view.bindOrderList(customerOrderService.getAllOrderByCustomerId(customerId));
        return view;
    }
    @GetMapping("/orders/{code}")
    public CustomerOrderDetailView orderDetail(HttpSession session,@PathVariable("code") String orderCode){
        CustomerOrderDetailView view=new CustomerOrderDetailView();
        if(!CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/login");
            return view;
        }
        long customerId=CustomerSession.getCustomerId(session);
        view.bindOrder(customerOrderService.getCustomerOrder(orderCode,customerId));
        return view;
    }
}
