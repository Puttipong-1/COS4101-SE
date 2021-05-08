package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.se.web.model.Product;
import ru.se.web.service.ManageProductService;
import ru.se.web.utility.EmpSession;
import ru.se.web.view.AddProductView;
import ru.se.web.view.EmployeeProductDetail;
import ru.se.web.view.ProductListView;

import javax.servlet.http.HttpSession;

@Controller
public class ManageProductController {
    private final ManageProductService manageProductService;
    private static final Logger LOGGER= LoggerFactory.getLogger(ManageProductController.class);
    @Autowired
    public ManageProductController(ManageProductService manageProductService) {
        this.manageProductService = manageProductService;
    }
    @GetMapping("/admin/products")
    public ProductListView productList(HttpSession session){
        ProductListView view=new ProductListView();
        if(!EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin/login");
            return view;
        }
        view.bindProductList(manageProductService.getAllProductAsc());
        return view;
    }
    @GetMapping("/admin/products/add")
    public AddProductView addProduct(HttpSession session){
        AddProductView view=new AddProductView();
        if (EmpSession.checkEmp(session)) {
            if(EmpSession.getEmpRole(session)!=2){
                view.bindViewName("redirect:/admin");
                return view;
            }
            view.bindProduct(new Product());
        }
        else{
            view.bindViewName("redirect:/admin/login");
        }
        return view;
    }
    @PostMapping("/admin/products/add")
    public String onAddProduct(HttpSession session,
                                       @ModelAttribute("product") Product product,
                                       @RequestParam("image") MultipartFile image){
       manageProductService.saveProduct(product,image);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/{code}")
    public EmployeeProductDetail productDetail(HttpSession session, @PathVariable("code") String productCode){
        EmployeeProductDetail view=new EmployeeProductDetail();
        if (EmpSession.checkEmp(session)) {
            if(EmpSession.getEmpRole(session)!=2){
                view.bindViewName("redirect:/admin");
                return view;
            }
            view.bindProduct(manageProductService.getProductByCode(productCode));
        }
        else{
            view.bindViewName("redirect:/admin/login");
        }
        return view;
    }
}
