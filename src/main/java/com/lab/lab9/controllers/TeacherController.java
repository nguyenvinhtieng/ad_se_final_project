package com.lab.lab9.controllers;

import com.lab.lab9.dao.*;
import com.lab.lab9.models.HocSinh;
import com.lab.lab9.models.LopHoc;
import com.lab.lab9.models.NamHoc;
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
@RequestMapping("/teacher")
public class TeacherController {
    private ThongBaoDAO thongBaoDAO;
    private LoaiThongBaoDAO loaiThongBaoDAO;
    private HocSinhDAO hocSinhDAO;
    private HocKyDAO hocKyDAO;
    private NamHocDAO namHocDAO;
    private TaiKhoanDAO taiKhoanDAO;
    private PhongHocDAO phongHocDAO;
    private LopHocDAO lopHocDAO;
    private GiaoVienDAO giaovienDAO;
    private GVCNDAO gvcnDAO;
    private MonHocDAO monHocDAO;
    private GiaoVienMonHocDAO giaoVienMonHocDAO;
    private BuoiThuTietDAO buoiThuTietDAO;
    private TkbDAO tkbDAO;
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String showHomePage(ModelMap modelMap,
                               @CookieValue(value = "username", defaultValue = "") String usernameCookie
    ){
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        return  "teacher/home";
    }

    @RequestMapping(value="/classes", method = RequestMethod.GET)
    public String showLopChuNhiem(ModelMap modelMap,
                          @CookieValue(value = "username", defaultValue = "") String usernameCookie
    ) throws SQLException, ClassNotFoundException {
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        lopHocDAO = new LopHocDAO();
        modelMap.addAttribute("lopchunhiem", lopHocDAO.layLopChuNhiem(usernameCookie));
        modelMap.addAttribute("lopbomon", lopHocDAO.layLopDayMon(usernameCookie));
        return  "teacher/classes";
    }

    @RequestMapping(value="/headteacher", method = RequestMethod.GET)
    public String showLopChuNhiem(ModelMap modelMap,
                          @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                          @RequestParam(value = "idlop") int idLop,
                                  @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                          HttpServletResponse response
    ) throws SQLException, ClassNotFoundException {
        if(!toastMessage.equals("")){
            String[] args = toastMessage.split("#");
            String type = args[0];
            String _message_ = args[1];
            String title = args[2];
            String message = String.join(" ", _message_.split("/"));
            Cookie cookie = new Cookie("toast_message", "");
            cookie.setPath("/teacher/headteacher");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            modelMap.addAttribute("type_toast", type);
            modelMap.addAttribute("title_toast", title);
            modelMap.addAttribute("message_toast", message);
        }
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }

        hocSinhDAO = new HocSinhDAO();
        modelMap.addAttribute("hocsinhlist", hocSinhDAO.layHocSinhCuaLop(idLop));
        return  "teacher/student_list";
    }

    @RequestMapping(value="/headteacher", method = RequestMethod.POST)
    public String themHocSinh(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                  @RequestParam(value = "idlop") String idLop,
                                @RequestParam(value = "id") String idHocSinh,
                              @CookieValue(value = "toast_message", defaultValue = "") String toastMessage,
                                  HttpServletResponse response
    ) throws SQLException, ClassNotFoundException {

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }


        String urlBack = "/teacher/headteacher?idlop=" + idLop;
        // check
        hocSinhDAO = new HocSinhDAO();
        boolean idHocSinhHopLe = hocSinhDAO.checkIdStudent(idHocSinh);
        if(idHocSinhHopLe){
            String toast = "error#ID/Student/not/valid!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/teacher/headteacher");
            response.addCookie(cookie_toast);
            return "redirect:" + urlBack;
        }
        lopHocDAO = new LopHocDAO();
        LopHoc l = lopHocDAO.layLopHoc(String.valueOf(idLop));
        int idNamHoc = l.getIdNamHoc();

        hocSinhDAO = new HocSinhDAO();
        boolean ktraHsinh = hocSinhDAO.kiemTraHocSinhCoHocLopKhacKhong(idHocSinh,idNamHoc);
        if(!ktraHsinh){
            String toast = "error#Student/is/study/in/another/class!#Error!";
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath("/teacher/headteacher");
            response.addCookie(cookie_toast);
            return "redirect:" + urlBack;
        }
        hocSinhDAO.themHocsinhVaoLop(idHocSinh, Integer.parseInt(idLop));
        String toast = "success#Add/student/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/teacher/headteacher");
        response.addCookie(cookie_toast);
        return "redirect:" + urlBack;
    }

    @RequestMapping(value="/delete-student", method = RequestMethod.GET)
    public String xoaHocSinh(ModelMap modelMap,
                                  @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                                  @RequestParam(value = "idlop") int idLop,
                             @RequestParam(value = "idhs") String idHs,
                                  HttpServletResponse response
    ) throws SQLException, ClassNotFoundException {

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String urlBack = "/teacher/headteacher?idlop=" + idLop;
        hocSinhDAO = new HocSinhDAO();
//        modelMap.addAttribute("hocsinhlist", hocSinhDAO.layHocSinhCuaLop(idLop));
        hocSinhDAO.xoaHocSinhKhoiLop(idLop,idHs);
        String toast = "success#Remove/student/successfully!#Success!";
        Cookie cookie_toast = new Cookie("toast_message", toast);
        cookie_toast.setPath("/teacher/headteacher");
        response.addCookie(cookie_toast);
        return "redirect:" + urlBack;
    }

    @RequestMapping(value="/tkb", method = RequestMethod.GET)
    public String showTKB(ModelMap modelMap,
                          @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                          @RequestParam(value = "idhocky" , defaultValue = "0") String idHocKy
    ) throws SQLException, ClassNotFoundException {
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }

        hocKyDAO = new HocKyDAO();
        namHocDAO = new NamHocDAO();
        lopHocDAO = new LopHocDAO();
        monHocDAO = new MonHocDAO();
        tkbDAO = new TkbDAO();
        buoiThuTietDAO = new BuoiThuTietDAO();
        hocKyDAO = new HocKyDAO();

        modelMap.addAttribute("buoi", buoiThuTietDAO.getAllBuoi());
        modelMap.addAttribute("thu", buoiThuTietDAO.getAllThu());
        modelMap.addAttribute("tiet", buoiThuTietDAO.getAllTiet());
        modelMap.addAttribute("monhoc", monHocDAO.getAllMonHoc());
        modelMap.addAttribute("hocky", hocKyDAO.getAllHocKy());
        modelMap.addAttribute("idhocky", Integer.parseInt(idHocKy));
        modelMap.addAttribute("thoikhoabieu", tkbDAO.layThoiKhoaBieuGv(usernameCookie,idHocKy));

        return  "teacher/tkb";
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
        giaovienDAO = new GiaoVienDAO();
//        HocSinhDAO hocSinhDAO = new HocSinhDAO();

        modelMap.addAttribute("giaovien", giaovienDAO.layThongTinGiaoVien(usernameCookie));

        return  "teacher/profile";
    }
}
