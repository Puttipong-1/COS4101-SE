package ru.se.web.view;

import org.apache.commons.collections4.ListUtils;
import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Product;

import java.util.List;

public class HomeView extends ModelAndView {
    public HomeView(){
        super("home");
    }
    public void bindProductList(List<Product> list){
        List<List<Product>> plist=ListUtils.partition(list,3);
        this.addObject("products",plist);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
