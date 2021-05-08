package ru.se.web.controller;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Product;
import ru.se.web.repositories.ProductRepository;
import ru.se.web.service.HomeService;
import ru.se.web.view.HomeView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController  {
    private HomeService homeService;
    @Autowired
    public HomeController(HomeService homeService){ this.homeService=homeService; }
    @RequestMapping("/")
    public HomeView home()
    {
        HomeView hv=new HomeView();
        hv.bindProductList(homeService.getAllProductDesc());
        return hv;
    }
}
