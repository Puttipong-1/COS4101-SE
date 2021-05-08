package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Cart;
import ru.se.web.model.Customer;

import java.util.List;

public class CartView extends ModelAndView {
    public CartView(){
        super("cart");
    }
    public void bindCustomerWithCart(Customer customer){
        this.addObject("customer",customer);
        int quantity=0;
        double total=0.0;
        for(Cart c:customer.getCarts()){
            quantity+=c.getQuantity();
            total+=c.getSumPrice();
        }
        this.addObject("delivery",50.0);
        this.addObject("total",total);
        this.addObject("quantity",quantity);
        this.addObject("totalOrder",total+50.0);
    }
    public void bindAddress(String address){
        this.addObject("address",address);
    }
    public void bindViewName(String name){ this.setViewName(name);}
}
