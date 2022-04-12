package com.lab.lab9.controllers;

import com.lab.lab9.dao.LoaiThongBaoDAO;
import com.lab.lab9.dao.TaiKhoanDAO;
import com.lab.lab9.dao.ThongBaoDAO;
import com.lab.lab9.models.LoaiThongBao;
import com.lab.lab9.models.ThongBao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private ThongBaoDAO thongBaoDAO;
    private LoaiThongBaoDAO loaiThongBaoDAO;
    // GET [/admin/home] => Hiển thị trang chủ admin
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String showHomePage(ModelMap modelMap,
                               @CookieValue(value = "username", defaultValue = "") String usernameCookie
    ){
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        return  "admin/home";
    }

    // GET [/admin/notify] => Hiển thị trang chủ admin
    @RequestMapping(value="/notify", method = RequestMethod.GET)
    public String showNotifyPage(ModelMap modelMap,
                               @CookieValue(value = "username", defaultValue = "") String usernameCookie
    )  throws SQLException, ClassNotFoundException{
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        thongBaoDAO = new ThongBaoDAO();
        loaiThongBaoDAO = new LoaiThongBaoDAO();

        modelMap.addAttribute("notifies", thongBaoDAO.getAllNotify());
        modelMap.addAttribute("types", loaiThongBaoDAO.getAllType());
        return  "admin/notify";
    }
    // POST [/admin/notify] => Tạo thông báo
    @RequestMapping(value="/notify", method = RequestMethod.POST)
    public String createNotify(ModelMap modelMap,
                               @RequestParam(value = "title") String tieuDe,
                               @RequestParam(value = "content") String noiDung,
                               @RequestParam(value = "category") String idLoaiThongBao,
                             @CookieValue(value = "username", defaultValue = "") String usernameCookie
    )  throws SQLException, ClassNotFoundException{
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        thongBaoDAO = new ThongBaoDAO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String ngayTao = dtf.format(now);
        thongBaoDAO.createNotify(tieuDe,noiDung , idLoaiThongBao, ngayTao);
        return  "redirect:/admin/notify";
    }
}
