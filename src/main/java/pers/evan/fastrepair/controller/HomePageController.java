package pers.evan.fastrepair.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.evan.fastrepair.exception.UserNotFoundException;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.service.EmployeeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Evan on 2016/1/17.
 */
@Controller
@Scope("prototype")
@RequestMapping("/home")
public class HomePageController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/index")
    String indexPage()
    {
        return "index";
    }

    @RequestMapping("/sign-in")
    String signInPage()
    {
        return "sign-in";
    }

    @RequestMapping("/reset-password")
    String resetPasswordPage()
    {
        return "reset-password";
    }

    @RequestMapping("/doSignIn")
    String doSignIn(HttpSession session, String username, String password)
    {
        if (username != null && password != null)
        {
            Employee employee;

            try {
                employee = employeeService.employeeLogin(username, password);
            } catch (UserNotFoundException e) {
                return "index";
            }

            if(employee != null)
            {
                session.setAttribute("employee", employee);
            }
        }
        return "index";
    }

}
