package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class AdminView extends ModelAndView {
    public AdminView(){
        super("admin");
    }
    public void bindStats(Map<String,Object> stats){
        this.addAllObjects(stats);
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
}
