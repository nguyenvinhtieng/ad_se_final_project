package com.lab.lab9.controllers;

import com.lab.lab9.dao.*;
import com.lab.lab9.models.LoaiThongBao;
import com.lab.lab9.models.LopHoc;
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
import java.sql.Array;
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
    private HocSinhDAO hocSinhDAO;
    private PhongHocDAO phongHocDAO;
    private LopHocDAO lopHocDAO;
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
                               @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                 @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                 HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/notify");
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
        return  "admin/notify";
    }
	
	// GET [/admin/notify/?id=] => Hiển thị trang chi tiết thông báo
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
        return  "admin/notify_detail";
    }

    // POST [/admin/notify] => Tạo thông báo
    @RequestMapping(value="/notify", method = RequestMethod.POST)
    public String createNotify(ModelMap modelMap,
                               @RequestParam(value = "title") String tieuDe,
                               @RequestParam(value = "content") String noiDung,
                               @RequestParam(value = "category") String idLoaiThongBao,
                             @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                               HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        thongBaoDAO = new ThongBaoDAO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String ngayTao = dtf.format(now);
        thongBaoDAO.createNotify(tieuDe,noiDung , idLoaiThongBao, ngayTao);

        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Create/notification/successfully!#Success";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/notify");
        response.addCookie(cookie_toast);

        return  "redirect:/admin/notify";
    }

    // GET [/admin/notify/delete?id=id] => Hiển thị trang chủ admin
    @RequestMapping(value="/notify/delete", method = RequestMethod.GET)
    public String deleteNoti(ModelMap modelMap,
                             @RequestParam(value = "id") String id,
                             HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        thongBaoDAO = new ThongBaoDAO();
        thongBaoDAO.deleteNotify(id);
        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Delete/notification/successfully!#Success";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/notify");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/notify";
    }

    // GET [/admin/account] => Hiển thị trang account
    @RequestMapping(value="/account", method = RequestMethod.GET)
    public String showAccountPage(ModelMap modelMap,
                                 @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                    @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                  HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        if(!toastMessage.equals("")){
            System.out.println("TOAST MESSAGE => " + toastMessage);
            String[] args = toastMessage.split("#");
            String type = args[0];
            String _message_ = args[1];
            String title = args[2];
            String message = String.join(" ", _message_.split("/"));
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/account");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            modelMap.addAttribute("type_toast", type);
            modelMap.addAttribute("title_toast", title);
            modelMap.addAttribute("message_toast", message);
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
        String toast = "success#Password/reset!#Success";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/account");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/account";
    }

    // GET [/admin/semester] => Hiển thị trang semester
    @RequestMapping(value="/semester", method = RequestMethod.GET)
    public String showSemesterPage(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                   @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                   HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }
        if(!toastMessage.equals("")){
            System.out.println("TOAST MESSAGE => " + toastMessage);
            String[] args = toastMessage.split("#");
            String type = args[0];
            String _message_ = args[1];
            String title = args[2];
            String message = String.join(" ", _message_.split("/"));
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/semester");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            modelMap.addAttribute("type_toast", type);
            modelMap.addAttribute("title_toast", title);
            modelMap.addAttribute("message_toast", message);
        }
        namHocDAO = new NamHocDAO();
        hocKyDAO = new HocKyDAO();
        modelMap.addAttribute("hocky", hocKyDAO.getAllHocKy());
        modelMap.addAttribute("namhoc", namHocDAO.getAllNamHoc());
        return  "admin/semester";
    }

    // POST [/admin/semester] => Tạo mới học kì
    @RequestMapping(value="/semester", method = RequestMethod.POST)
    public String addSemester(ModelMap modelMap,
                                   @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              @RequestParam(value="endday") String ngayKetThuc,
                              @RequestParam(value="name") String tenHocKy,
                              @RequestParam(value="startday") String ngayBatDau,
                              @RequestParam(value="schoolyear") String idNamHoc,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }
        hocKyDAO = new HocKyDAO();
        hocKyDAO.taoHocKy(tenHocKy, ngayBatDau, ngayKetThuc, idNamHoc);
        String toast = "success#Create/semester/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/semester");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/semester";
    }

    // GET [/admin/semester/delete?id=id] => Xóa học kỳ
    @RequestMapping(value="/semester/delete", method = RequestMethod.GET)
    public String deleteSemester(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              @RequestParam(value="id") String idHocKy,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }
        hocKyDAO = new HocKyDAO();
        hocKyDAO.xoaHocKy(Integer.parseInt(idHocKy.trim()));
        String toast = "success#Delete/semester/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/semester");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/semester";
    }
    // POST [/admin/semester/edit] => Sửa học kỳ
    @RequestMapping(value="/semester/edit", method = RequestMethod.POST)
    public String editSemester(ModelMap modelMap,
                                 @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                 @RequestParam(value="id") String idHocKy,
                               @RequestParam(value="name") String tenHocKy,
                               @RequestParam(value="startday") String ngayBatDau,
                               @RequestParam(value="endday") String ngayKetThuc,
                               @RequestParam(value="status") String trangThai,
                               @RequestParam(value="idnamhoc") String idNamHoc,
                                 HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }

        hocKyDAO = new HocKyDAO();
        if(trangThai.equals("ACTIVE") && !hocKyDAO.checkHocKyActive(idHocKy)){
            String toast = "error#Exist/semester/are/Active!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/admin/semester");
            response.addCookie(cookie_toast);
            return  "redirect:/admin/semester";
        }
        hocKyDAO.suaHocKy(idHocKy,tenHocKy,ngayBatDau, ngayKetThuc,trangThai,idNamHoc);
        String toast = "success#Edit/semester/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/semester");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/semester";
    }

    // GET [/admin/school-year] => Hiển thị trang năm học
    @RequestMapping(value="/school-year", method = RequestMethod.GET)
    public String showSchoolYear(ModelMap modelMap,
                                   @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                 @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                 HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
//        if(!usernameCookie.equals("ADMIN")){
//            return "redirect:/login";
//        }
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/school-year");
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
        String toast = "success#Create/school/year/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/school-year");
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
        String toast = "success#Delete/school/year/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/school-year");
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
        // Kiểm tra xem có năm học nào đang active không? => Nếu có r thì k chỉnh qua active được
        boolean isValidForActive = namHocDAO.checkNamHocActive(id);
        if(status.equals("ACTIVE") && !isValidForActive){
            // hiển thị lỗi
            String toast = "error#Exist/school/year/are/Active!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/admin/school-year");
            response.addCookie(cookie_toast);
            return  "redirect:/admin/school-year";
        }

        namHocDAO.suaNamHoc(id, name, startday, endday, status);
        // Tạo cookie => tạo toast => Hiển thị thông báo
        String toast = "success#Edit/school/year/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/school-year");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/school-year";
    }


    // GET [/admin/students] => Hiển thị trang quản lý học sinh
    @RequestMapping(value="/students", method = RequestMethod.GET)
    public String showStudentList(ModelMap modelMap,
                                       @CookieValue(value = "username", defaultValue = "") String usernameCookie,

                                       HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        hocSinhDAO = new HocSinhDAO();
        modelMap.addAttribute("students", hocSinhDAO.getAllStudent());
        return  "admin/students";
    }

    // GET [/admin/classroom] => Hiển thị trang quản lý phòng học
    @RequestMapping(value="/classroom", method = RequestMethod.GET)
    public String showClassroomPage(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                    @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                    HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/classroom");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
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
        phongHocDAO = new PhongHocDAO();
        modelMap.addAttribute("rooms", phongHocDAO.getALlPhongHoc());
        return  "admin/classroom";
    }

    // POST [/admin/classroom] => Thêm phòng học
    @RequestMapping(value="/classroom", method = RequestMethod.POST)
    public String addRoom(ModelMap modelMap,
                                    @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                          @RequestParam(value="name") String name,
                                    HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        phongHocDAO = new PhongHocDAO();
        phongHocDAO.taoPhongHoc(name);
        // hiển thị thoong baos
        String toast = "success#Create/room/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classroom");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classroom";
    }


    // GET [/admin/classroom/delete?id=id] => Xoa phòng học
    @RequestMapping(value="/classroom/delete", method = RequestMethod.GET)
    public String deleteRoom(ModelMap modelMap,
                          @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                          @RequestParam(value="id") String id,
                          HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        phongHocDAO = new PhongHocDAO();
        phongHocDAO.xoaPhongHoc(id);
        // hiển thị thoong baos
        String toast = "success#Delete/room/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classroom");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classroom";
    }

    // POST [/admin/classroom/edit] => Sửa phòng học
    @RequestMapping(value="/classroom/edit", method = RequestMethod.POST)
    public String editRoom(ModelMap modelMap,
                             @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                             @RequestParam(value="id") String id,
                           @RequestParam(value="name") String name,
                             HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        phongHocDAO = new PhongHocDAO();
        phongHocDAO.suaPhongHoc(id,name);
        // hiển thị thoong baos
        String toast = "success#Edit/room/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classroom");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classroom";
    }

    // GET [/admin/classes] => Hiển thị trang quản lý lớp học
    @RequestMapping(value="/classes", method = RequestMethod.GET)
    public String showClassPage(ModelMap modelMap,
                                    @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                    @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                    HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/classes");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
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
        lopHocDAO = new LopHocDAO();
        phongHocDAO = new PhongHocDAO();
        modelMap.addAttribute("classes", lopHocDAO.getALlLopHoc());
        modelMap.addAttribute("rooms", phongHocDAO.getALlPhongHoc());
        return  "admin/classes";
    }
    // GET [/admin/classes/delete?id=id] => Xóa phòng học
    @RequestMapping(value="/classes/delete", method = RequestMethod.GET)
    public String deleteClass(ModelMap modelMap,
                           @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                           @RequestParam(value="id") String id,
                           HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        lopHocDAO.xoaLopHoc(id);
        // hiển thị thoong baos
        String toast = "success#Delete/class/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classes");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classes";
    }
    // POST [/admin/classes] => Tạo Lớp học
    @RequestMapping(value="/classes", method = RequestMethod.POST)
    public String createClass(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="khoi") String khoi,
                              @RequestParam(value="id-room") String idPhong,
                              @RequestParam(value="namVaoTruong") String namVaoTruong,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        lopHocDAO.taoLopHoc(name, khoi, idPhong, namVaoTruong);
        // hiển thị thoong baos
        String toast = "success#Create/class/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classes");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classes";
    }

    // POST [/admin/classes/edit] => Sửa Lớp học
    @RequestMapping(value="/classes/edit", method = RequestMethod.POST)
    public String editClass(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                            @RequestParam(value="id") String id,
                            @RequestParam(value="name") String name,
                              @RequestParam(value="khoi") String khoi,
                              @RequestParam(value="id-room") String idPhong,
                              @RequestParam(value="namVaoTruong") String namVaoTruong,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        lopHocDAO.suaLopHoc(name, khoi, idPhong, namVaoTruong, id);
        // hiển thị thoong baos
        String toast = "success#Edit/class/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classes");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classes";
    }

}
