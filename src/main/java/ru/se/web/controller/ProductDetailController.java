package ru.se.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.se.web.service.ProductDetailService;
import ru.se.web.utility.CustomerSession;
import ru.se.web.view.ProductDetailView;

import javax.servlet.http.HttpSession;

@Controller
public class ProductDetailController {
    private ProductDetailService productDetailService;
    @Autowired
    public ProductDetailController(ProductDetailService productDetailService){
        this.productDetailService=productDetailService;
    }
    @GetMapping("/product/{code}")
    public ProductDetailView productDetail(HttpSession session,@PathVariable("code") String productCode){
        ProductDetailView view=new ProductDetailView();
        if(!CustomerSession.checkCustomer(session)){
            view.bindViewName("redirect:/login");
            return view;
        }
        view.bindProduct(productDetailService.getProductByProductCode(productCode));
        return view;
    }
}
