package com.lab.lab9.controllers;

import com.lab.lab9.dao.*;
import com.lab.lab9.models.LoaiThongBao;
import com.lab.lab9.models.ThongBao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
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
    private TaiKhoanDAO taiKhoanDAO;
    private NamHocDAO namHocDAO;
    private HocKyDAO hocKyDAO;
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

    // GET [/admin/notify] => Hiển thị trang thông báo
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
	
	// GET [/admin/notify/?id=] => Hiển thị trang chi tiết thông báo
    @RequestMapping(value="/notify/detail", method = RequestMethod.GET)
    public String showDetailNotifyPage(ModelMap modelMap,
                               @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                               @RequestParam(value = "id") int notify_id
    )  throws SQLException, ClassNotFoundException{
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        
        thongBaoDAO = new ThongBaoDAO();

        modelMap.addAttribute("notifies_detail", thongBaoDAO.detailNotify(notify_id));
        return  "admin/notify_detail";
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

    // GET [/admin/notify/delete?id=id] => Hiển thị trang chủ admin
    @RequestMapping(value="/notify/delete", method = RequestMethod.GET)
    public String deleteNoti(ModelMap modelMap,
                             @RequestParam(value = "id") String id
    )  throws SQLException, ClassNotFoundException{
        thongBaoDAO = new ThongBaoDAO();
        thongBaoDAO.deleteNotify(id);
        return  "redirect:/admin/notify";
    }

    // GET [/admin/account] => Hiển thị trang account
    @RequestMapping(value="/account", method = RequestMethod.GET)
    public String showAccountPage(ModelMap modelMap,
                                 @CookieValue(value = "username", defaultValue = "") String usernameCookie
    )  throws SQLException, ClassNotFoundException{
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        taiKhoanDAO = new TaiKhoanDAO();
        modelMap.addAttribute("accounts", taiKhoanDAO.getAllAccount());
        return  "admin/account";
    }

    // GET [/admin/account/reset?username=username] => Reset password
    @RequestMapping(value="/account/reset", method = RequestMethod.GET)
    public String resetPasswordAccount(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                       @RequestParam(value = "username") String username,
                                       HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String password = username;
        taiKhoanDAO = new TaiKhoanDAO();
        taiKhoanDAO.resetPassword(username, password);
        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Password/reset!";
        Cookie cookie_toast = new Cookie("TOAST-MESSAGE", toast);
        response.addCookie(cookie_toast);
        return  "redirect:/admin/account";
    }

    // GET [/admin/semester] => Hiển thị trang semester
    @RequestMapping(value="/semester", method = RequestMethod.GET)
    public String showSemesterPage(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }
        hocKyDAO = new HocKyDAO();
        modelMap.addAttribute("hocky", hocKyDAO.getAllHocKy());
        return  "admin/semester";
    }

    // GET [/admin/school-year] => Hiển thị trang năm học
    @RequestMapping(value="/school-year", method = RequestMethod.GET)
    public String showSchoolYear(ModelMap modelMap,
                                   @CookieValue(value = "username", defaultValue = "") String usernameCookie
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }
        namHocDAO = new NamHocDAO();
        modelMap.addAttribute("namhoc", namHocDAO.getAllNamHoc());
        return  "admin/school-year";
    }

    // POST [/admin/school-year] => Taọ mới năm học
    @RequestMapping(value="/school-year", method = RequestMethod.POST)
    public String createSchoolYear(ModelMap modelMap,
                                 @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                   @RequestParam(value="name") String tenNamHoc,
                                   @RequestParam(value="startday") String ngayBatDau,
                                   @RequestParam(value="endday") String ngayKetThuc,
                                   HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        // Kiểm tra tên năm học có bị trùng hay không? => Có thể không cần thiêt


        namHocDAO = new NamHocDAO();
        namHocDAO.taoNamHoc(tenNamHoc, ngayBatDau, ngayKetThuc);

        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Create/school/year/successfully!";
        Cookie cookie_toast = new Cookie("TOAST-MESSAGE", toast);
        response.addCookie(cookie_toast);

        return  "redirect:/admin/school-year";
    }

    // GET [/admin/school-year/delete?id=id] => Xoá Năm Học
    @RequestMapping(value="/school-year/delete", method = RequestMethod.GET)
    public String deleteSchoolYear(ModelMap modelMap,
                                   @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                   @RequestParam(value="id") String id,
                                   HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        // Kiểm tra có thể xóa hay không? => Tìm trong bảng Học Kỳ => Làm sau
        // Hiển thị thông báo lỗi nếu có

        namHocDAO = new NamHocDAO();
        namHocDAO.xoaNamHoc(Integer.parseInt(id));

        // Tạo cookie => tạo toast => Hiển thị thông báo
        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Delete/school/year/successfully!";
        Cookie cookie_toast = new Cookie("TOAST-MESSAGE", toast);
        response.addCookie(cookie_toast);

        return  "redirect:/admin/school-year";
    }
    // POST [/admin/school-year/edit] => Sửa Năm Học
    @RequestMapping(value="/school-year/edit", method = RequestMethod.POST)
    public String editSchoolYear(ModelMap modelMap,
                                   @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                   @RequestParam(value="id") String id,
                                 @RequestParam(value="name") String name,
                                 @RequestParam(value="startday") String startday,
                                 @RequestParam(value="endday") String endday,
                                 @RequestParam(value="status") String status,
                                 HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        namHocDAO = new NamHocDAO();
        // Kiểm tra xem có học kỳ nào đang active không? => Nếu có r thì k chỉnh qua active được
        boolean isValidForActive = namHocDAO.checkNamHocActive(id);
        if(status.equals("ACTIVE") && !isValidForActive){
            // hiển thị lỗi

            return  "redirect:/admin/school-year";
        }

        namHocDAO.suaNamHoc(id, name, startday, endday, status);
        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Edit/school/year/successfully!";
        Cookie cookie_toast = new Cookie("TOAST-MESSAGE", toast);
        response.addCookie(cookie_toast);

        return  "redirect:/admin/school-year";
    }

}
