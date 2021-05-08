package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Product;

public class EmployeeProductDetail extends ModelAndView {
    public EmployeeProductDetail(){
        super("employeeproductdetail");
    }
    public void bindProduct(Product product){
        this.addObject("product",product);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }

}
