package ru.se.web.utility;

import ru.se.web.model.Employee;

import javax.servlet.http.HttpSession;

public class EmpSession {
    public static boolean checkEmp(HttpSession session){
        if(session!=null){
            return session.getAttribute("employee") !=null;
        }
        return false;
    }
    public static long getEmpId(HttpSession session){
        if(session!=null){
            if(session.getAttribute("employee")!=null){
                return (long) session.getAttribute("employee");
            }
        }
        return 0;
    }
    public static int getEmpRole(HttpSession session){
        if(session!=null){
            if(session.getAttribute("role")!=null){
                return (int) session.getAttribute("role");
            }
        }
        return 0;
    }
    public static void saveEmp(HttpSession session, Employee employee){
        session.setAttribute("role",employee.getRole());
        session.setAttribute("employee",employee.getEmployeeId());
    }
    public static void EmpLogout(HttpSession session){
        if(session!=null){
            session.removeAttribute("role");
            session.removeAttribute("employee");
        }
    }
}
