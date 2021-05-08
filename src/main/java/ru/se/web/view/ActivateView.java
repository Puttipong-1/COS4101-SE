package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;

public class ActivateView extends ModelAndView {
    public ActivateView(){
        super("activate");
    }
    public void bindViewName(String view){
        this.setViewName(view);
    }
    public void bindMsg(String msg){
        this.addObject("msg",msg);
    }
}
