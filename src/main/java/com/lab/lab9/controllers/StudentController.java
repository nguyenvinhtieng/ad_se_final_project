package com.lab.lab9.controllers;


import com.lab.lab9.dao.HocSinhDAO;
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

    // GET [/student/notification] => Hiển thị trang thông báo
    @RequestMapping(value="/notification", method = RequestMethod.GET)
    public String showNotifyPage(ModelMap modelMap,
                                 @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                 @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                 HttpServletResponse response,
                                 @RequestParam(value = "page", defaultValue = "0") String page,
                                 @RequestParam(value = "cate", defaultValue = "") String cate,
                                 @RequestParam(value = "title", defaultValue = "") String title
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        int pageNumber = Integer.parseInt(page);
        if(pageNumber < 0) {
            page = "0";
            pageNumber = 0;
        }

        thongBaoDAO = new ThongBaoDAO();
        loaiThongBaoDAO = new LoaiThongBaoDAO();
        modelMap.addAttribute("notifies", thongBaoDAO.getTenNotify(page, cate, title));
        modelMap.addAttribute("page", page);

        String textPag = "?cate="+cate+"&title="+title+"&page=";
        String textPrev = textPag + String.valueOf(pageNumber <= 0 ? 0 : pageNumber - 1);
        String textNext = textPag + String.valueOf(pageNumber + 1);

        modelMap.addAttribute("prevpage", textPrev);
        modelMap.addAttribute("nextpage", textNext);

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

    // GET [/student/profile] => Hiển thị trang thong tin hoc sinh
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String showProfile(ModelMap modelMap,
                                       @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                       HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        HocSinhDAO hocSinhDAO = new HocSinhDAO();

        modelMap.addAttribute("student", hocSinhDAO.getHocSinhData(usernameCookie));

        return  "student/profile";
    }
}
