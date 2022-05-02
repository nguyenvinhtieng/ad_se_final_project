package com.lab.lab9.controllers;


import com.lab.lab9.dao.LoaiThongBaoDAO;
import com.lab.lab9.dao.ThongBaoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
@RequestMapping("/student")
public class StudentController {

    private ThongBaoDAO thongBaoDAO;
    private LoaiThongBaoDAO loaiThongBaoDAO;


    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String showHomePage(ModelMap modelMap,
                               @CookieValue(value = "username", defaultValue = "") String usernameCookie
    ){
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        return  "student/home";
    }

    // GET [/student/notify] => Hiển thị trang thông báo
    @RequestMapping(value="/notify", method = RequestMethod.GET)
    public String showNotifyPage(ModelMap modelMap,
                                 @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                 @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                 HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/student/notify");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            System.out.println("TOAST MESSAGE => " + toastMessage);
            String[] args = toastMessage.split("#");
            String type = args[0];
            String _message_ = args[1];
            String title = args[2];
            String message = String.join(" ", _message_.split("/"));

            modelMap.addAttribute("type_toast", type);
            modelMap.addAttribute("title_toast", title);
            modelMap.addAttribute("message_toast", message);
        }
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        thongBaoDAO = new ThongBaoDAO();
        loaiThongBaoDAO = new LoaiThongBaoDAO();

        modelMap.addAttribute("notifies", thongBaoDAO.getAllNotify());
        modelMap.addAttribute("types", loaiThongBaoDAO.getAllType());
        return  "student/notify";
    }

    // GET [/student/notify/?id=] => Hiển thị trang chi tiết thông báo
    @RequestMapping(value="/notify/detail", method = RequestMethod.GET)
    public String showDetailNotifyPage(ModelMap modelMap,
                                       @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                       @RequestParam(value = "id") int notify_id,
                                       HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        thongBaoDAO = new ThongBaoDAO();
        modelMap.addAttribute("notifies_detail", thongBaoDAO.detailNotify(notify_id));
        return  "student/notify_detail";
    }
}
