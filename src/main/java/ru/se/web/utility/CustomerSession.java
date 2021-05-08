package ru.se.web.utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.se.web.model.Customer;

import javax.servlet.http.HttpSession;

public class CustomerSession {
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerSession.class);
    public static long getCustomerId(HttpSession session){
        if(session!=null){
            if (session.getAttribute("customer")!=null) {
                return (long) session.getAttribute("customer");
            }
        }
        return 0;
    }
    public static Boolean checkCustomer(HttpSession session){
        if(session!=null){
            return session.getAttribute("customer") != null;
        }
        return false;
    }
    public static void saveCustomer(HttpSession session, Customer customer){
        session.setAttribute("customer",customer.getCustomerId());
    }
    public static void customerLogout(HttpSession session){
        if(session!=null){
            session.removeAttribute("customer");
        }
    }
}
