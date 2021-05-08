package ru.se.web.view;

import org.springframework.web.servlet.ModelAndView;
import ru.se.web.model.Order;
import java.util.List;

public class EmployeeOrderListView extends ModelAndView {
    public EmployeeOrderListView(){
        super("orderlist");
    }
    public void bindOrderList(List<Order> orders){
        this.addObject("orders",orders);
    }
    public void bindStatus(int status){
        switch (status){
            case 1:this.addObject("title","รายการคำสั่งชื้อใหม่");break;
            case 2:this.addObject("title","รายการคำสั่งชื้อที่ยืนยันแล้ว");break;
            case 3:this.addObject("title","รายการคำสั่งชื้อที่จัดส่งสำเร็จ");break;
            case 4:this.addObject("title","รายการคำสั่งชื้อที่ยกเลิก");break;
        }
    }
    public void bindViewName(String name){
        this.setViewName(name);
    }
}
