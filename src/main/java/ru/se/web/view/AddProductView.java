package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Product;

public class AddProductView extends ModelAndView {
    public AddProductView(){
        super("addproduct");
    }
    public void bindProduct(Product product){
        this.addObject("product",product);
    }
    public void bindViewName(String name){
        this.setViewName(name);
    }
}
