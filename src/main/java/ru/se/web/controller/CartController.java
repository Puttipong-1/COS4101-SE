package ru.se.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.se.web.model.Customer;
import ru.se.web.model.Product;
import ru.se.web.service.CartService;
import ru.se.web.utility.CustomerSession;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    private final CartService cartService;
    private static final Logger LOGGER= LoggerFactory.getLogger(CartController.class);
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/addcart")
    public String onAddCart(HttpSession session,
                              @ModelAttribute("product") Product product,
                              @RequestParam("quantity") int quantity){
        long customerId= CustomerSession.getCustomerId(session);
        cartService.addCart(customerId,product,quantity);
        return "redirect:/";
    }
    @PostMapping("/removecart")
    public String onRemoveCart(@RequestParam("customer") long customerId,
                            @RequestParam("product") long productId){
        cartService.removeCart(customerId,productId);
        return "redirect:/makeorder";
    }
}
