package ru.se.web.view;


import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Product;

public class ProductDetailView extends ModelAndView {
    public ProductDetailView(){
        super("productdetail");
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
    public void bindProduct(Product product){
        if(product==null){
            this.setViewName("redirect:/error");
        }else{
            this.addObject("product",product);
        }
    }
}
