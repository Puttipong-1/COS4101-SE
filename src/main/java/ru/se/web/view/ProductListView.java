package ru.se.web.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Product;

import java.util.List;

public class ProductListView extends ModelAndView {
    public ProductListView(){
        super("productlist");
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
    public void bindProductList(List<Product> productList) {
        this.addObject("products",productList);}
}
