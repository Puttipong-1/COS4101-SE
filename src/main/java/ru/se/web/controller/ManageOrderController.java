package ru.se.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.se.web.model.Company;
import ru.se.web.model.Order;
import ru.se.web.service.ManageOrderService;
import ru.se.web.utility.EmpSession;
import ru.se.web.view.EmployeeOrderDetailView;
import ru.se.web.view.EmployeeOrderListView;
import ru.se.web.view.PrintOrderView;

import javax.servlet.http.HttpSession;

@Controller
public class ManageOrderController {
    private final ManageOrderService manageOrderService;
    @Autowired
    public ManageOrderController(ManageOrderService manageOrderService) {
        this.manageOrderService = manageOrderService;
    }
    @GetMapping("/admin/orders/{status}")
    public EmployeeOrderListView orderList(HttpSession session,@PathVariable("status") int status){
        EmployeeOrderListView view=new EmployeeOrderListView();
        if(!EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin/login");
            return view;
        }
        if(status<1||status>4){
            view.bindViewName("error");
            return view;
        }
        view.bindOrderList(manageOrderService.getOrderListByStatusASC(status));
        view.bindStatus(status);
        return view;
    }
    @GetMapping("/admin/orders/detail/{code}")
    public EmployeeOrderDetailView orderDetail(HttpSession session,@PathVariable("code") String orderCode){
        EmployeeOrderDetailView view=new EmployeeOrderDetailView();
        if(!EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin/login");
            return view;
        }
        view.bindOrder(manageOrderService.getOrderByCode(orderCode));
        return view;
    }
    @PostMapping("/admin/orders/confirm")
    public String onConfirmOrder(@RequestParam("orderId") long orderId,@RequestParam("orderCode") String orderCode){
        manageOrderService.updateOrderStatus(orderId,2);
        return "redirect:/admin/orders/print/"+orderCode;
    }
    @PostMapping("/admin/orders/cancel")
    public String onCancelOrder(@RequestParam("orderId") long orderId){
        manageOrderService.updateOrderStatus(orderId,4);
        return "redirect:/admin/orders/1";
    }
    @PostMapping("/admin/orders/delivery")
    public String onDeliverOrder(@RequestParam("orderId") long orderId){
        manageOrderService.updateOrderStatus(orderId,3);
        return "redirect:/admin/orders/2";
    }
    @GetMapping("/admin/orders/print/{code}")
    public PrintOrderView printOrder(HttpSession session, @PathVariable("code") String orderCode){
        PrintOrderView view=new PrintOrderView();
        if(!EmpSession.checkEmp(session)){
            view.bindViewName("redirect:/admin/login");
            return view;
        }
        Order order=manageOrderService.getOrderByCode(orderCode);
        Company company=manageOrderService.getCompany();
        view.bindOrderAndCompany(order,company);
        return view;
    }
}
