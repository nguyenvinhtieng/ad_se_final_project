package com.lab.lab9.controllers;

import com.lab.lab9.dao.*;
import com.lab.lab9.models.LoaiThongBao;
import com.lab.lab9.models.LopHoc;
import com.lab.lab9.models.PhongHoc;
import com.lab.lab9.models.ThongBao;
import com.lab.lab9.utils.GenerateId;
import com.lab.lab9.utils.Hashing;
import com.lab.lab9.utils.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
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
    private GiaoVienDAO giaovienDAO;
    private GVCNDAO gvcnDAO;
    private MonHocDAO monHocDAO;
    private GiaoVienMonHocDAO giaoVienMonHocDAO;
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

        // Tạo cookie => tạo toast => Hiển thị thông báo //error
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
    )  throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String password = Hashing.hashPassword(username);
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
        PhongHoc p = phongHocDAO.layPhongHoc(id);
        if(p.getTrangThai() > 0){
            String toast = "error#Can't/delete/this/room!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/admin/classroom");
            response.addCookie(cookie_toast);
            return  "redirect:/admin/classroom";
        }

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
        namHocDAO = new NamHocDAO();
        modelMap.addAttribute("classes", lopHocDAO.getALlLopHoc());
        modelMap.addAttribute("rooms", phongHocDAO.getALlPhongHocTrong());
        modelMap.addAttribute("namhoc", namHocDAO.getAllNamHoc());
        return  "admin/classes";
    }
    // GET [/admin/classes/delete?id=id] => Xóa lớp học
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
        phongHocDAO = new PhongHocDAO();
        LopHoc l = lopHocDAO.layLopHoc(id);
        System.out.println(l.toString());
        if(l.getIdPhongHoc() > 0){
            phongHocDAO.doiTrangThaiPhong(String.valueOf(l.getIdPhongHoc()),"0");
        }
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
                              @RequestParam(value="idNamHoc") String idNamHoc,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        String idPhong = "0";
        lopHocDAO.taoLopHoc(name, khoi, idPhong, idNamHoc);
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
                              @RequestParam(value="idNamHoc") String idNamHoc,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        phongHocDAO = new PhongHocDAO();
        LopHoc l = lopHocDAO.layLopHoc(id);
        if(l.getIdPhongHoc() > 0){
            phongHocDAO.doiTrangThaiPhong(String.valueOf(l.getIdPhongHoc()),"0");
        }
        phongHocDAO.doiTrangThaiPhong(idPhong,id);
        lopHocDAO.suaLopHoc(name, khoi, idPhong, idNamHoc, id);
        // hiển thị thoong baos
        String toast = "success#Edit/class/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/classes");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/classes";
    }


    // GET [/admin/students] => Hiển thị trang quản lý học sinh
    @RequestMapping(value="/students", method = RequestMethod.GET)
    public String showStudentList(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                  @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                  HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/students");
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
        hocSinhDAO = new HocSinhDAO();
        modelMap.addAttribute("students", hocSinhDAO.getAllStudent());
        return  "admin/students";
    }

    // POST [/admin/students] => Thêm học sinh
    @RequestMapping(value="/students", method = RequestMethod.POST)
    public String createClass(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              @RequestParam(value="id") String id,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="date") String date,
                              @RequestParam(value="sex") String sex,
                              @RequestParam(value="originalplace") String originalplace,
                              @RequestParam(value="nation") String nation,
                              @RequestParam(value="household") String household,
                              @RequestParam(value="phone") String phone,
                              @RequestParam("avatar") MultipartFile avatar,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException {

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String fileNameNew = GenerateId.getRandomString(10) + ".png";
        UploadFile.saveFile(fileNameNew, avatar);
        String hashPass = Hashing.hashPassword(id);
        taiKhoanDAO = new TaiKhoanDAO();
        boolean isValidAccount = taiKhoanDAO.kiemTraUsername(id);
        if(!isValidAccount){
            String toast = "error#Username/was/exists!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/admin/students");
            response.addCookie(cookie_toast);
            return  "redirect:/admin/students";
        }
        taiKhoanDAO.taoTaiKhoan(id, hashPass, "HOCSINH");
        hocSinhDAO = new HocSinhDAO();
        hocSinhDAO.addStudent(id, name, date, sex, originalplace, nation, household, phone, fileNameNew);
        // hiển thị thoong baos
        String toast = "success#Add/student/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/students");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/students";
    }

    // POST [/admin/students/edit] => Sửa Học Sinh
    @RequestMapping(value="/students/edit", method = RequestMethod.POST)
    public String editClass(ModelMap modelMap,
                            @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                            @RequestParam(value="id") String id,
                            @RequestParam(value="name") String name,
                            @RequestParam(value="date") String date,
                            @RequestParam(value="sex") String sex,
                            @RequestParam(value="originalplace") String originalplace,
                            @RequestParam(value="nation") String nation,
                            @RequestParam(value="household") String household,
                            @RequestParam(value="phone") String phone,
                            @RequestParam("avatar") MultipartFile avatar,
                            @RequestParam("status") String status,
                            HttpServletResponse response
    )  throws SQLException, ClassNotFoundException, IOException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        long fileSize = avatar.getSize();
        String fileNameNew = "";
        if(fileSize > 0) {
            fileNameNew = GenerateId.getRandomString(10) + ".png";
            UploadFile.saveFile(fileNameNew, avatar);
        }
        hocSinhDAO = new HocSinhDAO();
        hocSinhDAO.editStudent(id, name, date, sex, originalplace, nation, household, phone, fileNameNew, status);
        // hiển thị thoong baos
        String toast = "success#Edit/student/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/students");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/students";
    }

    // ======================================
    // GET [/admin/teachers] => Hiển thị trang quản lý giáo viên
    @RequestMapping(value="/teachers", method = RequestMethod.GET)
    public String shoưTeacherList(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                  @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                  HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/teachers");
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
        giaovienDAO = new GiaoVienDAO();
        modelMap.addAttribute("teachers", giaovienDAO.getAllTeacher());
        return  "admin/teachers";
    }


    // POST [/admin/teachers] => Thêm giáo viên
    @RequestMapping(value="/teachers", method = RequestMethod.POST)
    public String createClass(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              @RequestParam(value="id") String id,
                              @RequestParam(value="name") String name,
                              @RequestParam(value="identity") String identity,
                              @RequestParam(value="date") String date,
                              @RequestParam(value="sex") String sex,
                              @RequestParam(value="originalplace") String originalplace,
                              @RequestParam(value="nation") String nation,
                              @RequestParam(value="household") String household,
                              @RequestParam(value="phone") String phone,
                              @RequestParam(value="email") String email,
                              @RequestParam("avatar") MultipartFile avatar,
                                HttpServletResponse response
    )  throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException {

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String fileNameNew = GenerateId.getRandomString(10) + ".png";
        UploadFile.saveFile(fileNameNew, avatar);
        String hashPass = Hashing.hashPassword(id);
        taiKhoanDAO = new TaiKhoanDAO();
        boolean isValidAccount = taiKhoanDAO.kiemTraUsername(id);
        if(!isValidAccount){
            String toast = "error#Username/was/exists!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/admin/teachers");
            response.addCookie(cookie_toast);
            return  "redirect:/admin/teachers";
        }
        taiKhoanDAO.taoTaiKhoan(id, hashPass, "GIAOVIEN");
        giaovienDAO = new GiaoVienDAO();
        giaovienDAO.themGiaoVien(id, name, identity, date, sex, originalplace, nation, household, phone, email, fileNameNew);
        // hiển thị thoong baos
        String toast = "success#Add/teacher/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/teachers");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/teachers";
    }

    // POST [/admin/teachers/edit] => Sửa Giáo Viên
    @RequestMapping(value="/teachers/edit", method = RequestMethod.POST)
    public String editClass(ModelMap modelMap,
                            @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                            @RequestParam(value="id") String id,
                            @RequestParam(value="name") String name,
                            @RequestParam(value="identity") String identity,
                            @RequestParam(value="date") String date,
                            @RequestParam(value="sex") String sex,
                            @RequestParam(value="originalplace") String originalplace,
                            @RequestParam(value="nation") String nation,
                            @RequestParam(value="household") String household,
                            @RequestParam(value="phone") String phone,
                            @RequestParam(value="email") String email,
                            @RequestParam("avatar") MultipartFile avatar,
    HttpServletResponse response
    )  throws SQLException, ClassNotFoundException, IOException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        long fileSize = avatar.getSize();
        String fileNameNew = "";
        if(fileSize > 0) {
            fileNameNew = GenerateId.getRandomString(10) + ".png";
            UploadFile.saveFile(fileNameNew, avatar);
        }
        giaovienDAO = new GiaoVienDAO();
        giaovienDAO.suaGiaoVien(name, identity, date, sex, originalplace, nation, household, phone, email, fileNameNew, id);
        // hiển thị thoong baos
        String toast = "success#Edit/teacher/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/teachers");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/teachers";
    }

    // GET [/admin/gvcn] => Hiển thị trang giáo viên chủ nhiệm
    @RequestMapping(value="/gvcn", method = RequestMethod.GET)
    public String showGVCN(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                  @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                  HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{
        if(!toastMessage.equals("")){
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/admin/gvcn");
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
        gvcnDAO = new GVCNDAO();
        giaovienDAO = new GiaoVienDAO();
        modelMap.addAttribute("classes", gvcnDAO.getDataGVCNPage());
        modelMap.addAttribute("teachers", giaovienDAO.getAllTeacherAvtice());
        return  "admin/gvcn";
    }

    // POST [/admin/gvcn] => Sửa giáo viên chủ nhiệm
    @RequestMapping(value="/gvcn", method = RequestMethod.POST)
    public String editGVCN(ModelMap modelMap,
                           @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                           HttpServletResponse response,
                           @RequestParam(value="class") String classID,
                           @RequestParam(value="gvcn") String gvcnUsername
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        gvcnDAO = new GVCNDAO();
        boolean coGvcn = gvcnDAO.kiemTraLopCoGVCNChua(classID);
        System.out.println(coGvcn);
        if(coGvcn){
            gvcnDAO.doiGVCN(classID,gvcnUsername);
        }else{
            gvcnDAO.themGVCN(classID,gvcnUsername);
        }
        // hiển thị thoong baos
        String toast = "success#Choose/GVCN/successfully!!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/admin/gvcn");
        response.addCookie(cookie_toast);
        return  "redirect:/admin/gvcn";
    }

    // GET [/admin/subject-teacher] => Hiển thị trang giáo viên bộ môn - Danh sách các lớp
    @RequestMapping(value="/subject-teacher", method = RequestMethod.GET)
    public String showSubjectTeacher(ModelMap modelMap,
                           @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                           @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                           HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        int[] grades = {10, 11, 12};
        lopHocDAO = new LopHocDAO();
        modelMap.addAttribute("grades", grades);
        modelMap.addAttribute("classes", lopHocDAO.getALlLopHoc());
        return  "admin/subject_teacher";
    }
    // GET [/admin/subject-teacher] => Hiển thị trang giáo viên bộ môn theo từng lớp
    @RequestMapping(value="/subject-teacher/detail", method = RequestMethod.GET)
    public String showSubjectTeacherDetail(ModelMap modelMap,
                                     @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                     @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                     HttpServletResponse response,
                                   @RequestParam(value="classid") String classId
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        monHocDAO = new MonHocDAO();
        giaoVienMonHocDAO = new GiaoVienMonHocDAO();
        giaovienDAO = new GiaoVienDAO();
        LopHoc l = lopHocDAO.layLopHoc(classId);
        if(l.getTenLop().equals("")){ // Không tìm thấy lớp học
            return  "redirect:/admin/subject_teacher";
        }
        modelMap.addAttribute("lop", l);
        modelMap.addAttribute("monhoc", monHocDAO.getAllMonHoc());
        modelMap.addAttribute("giaovienmonhoc", giaoVienMonHocDAO.layGiaoVienMonHocTheoLop(classId));
        modelMap.addAttribute("teachers", giaovienDAO.getAllTeacherAvtice());

        return  "admin/subject_teacher_detail";
    }

    // GET [/admin/subject-teacher] => Hiển thị trang giáo viên bộ môn theo từng lớp
    @RequestMapping(value="/subject-teacher/detail", method = RequestMethod.POST)
    public String editSubjectTeacher(ModelMap modelMap,
                                           @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                           @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                           HttpServletResponse response,
                                           @RequestParam(value="classid") String classId,
                                            @RequestParam(value="subjectid") String subjectId,
                                     @RequestParam(value="usernamegv") String usernameGv
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String redirectUrl = "redirect:/admin/subject-teacher/detail?classid=" + classId;
        giaoVienMonHocDAO = new GiaoVienMonHocDAO();
        boolean coGiaoVien = giaoVienMonHocDAO.coGiaoVienMonHocChua(classId, subjectId);
        if(coGiaoVien){
            // Sửa
            giaoVienMonHocDAO.suaGiaoVienMonHoc(classId, subjectId, usernameGv);
        }else{
            // Tạo mới
            giaoVienMonHocDAO.themGiaoVienMonHoc(classId, subjectId, usernameGv);
        }

        return  redirectUrl;
    }


}
