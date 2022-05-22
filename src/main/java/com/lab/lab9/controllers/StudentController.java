package com.lab.lab9.controllers;


import com.lab.lab9.dao.*;
import com.lab.lab9.models.HocKy;
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
@RequestMapping("/student")
public class StudentController {

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

    // GET [/student/tkb] => Hiển thị trang thoi khoa bieu
    @RequestMapping(value="/tkb", method = RequestMethod.GET)
    public String showTkb(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              @RequestParam(value = "idhocky" , defaultValue = "0") String idHocKy,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        hocSinhDAO = new HocSinhDAO();
        hocKyDAO = new HocKyDAO();
        namHocDAO = new NamHocDAO();
        lopHocDAO = new LopHocDAO();
        monHocDAO = new MonHocDAO();
        tkbDAO = new TkbDAO();
        NamHoc namHoc = namHocDAO.layNamHocActive();
        buoiThuTietDAO = new BuoiThuTietDAO();
        HocSinh hs = hocSinhDAO.getHocSinhData(usernameCookie);

        LopHoc l = lopHocDAO.layLopHocDuaVaoIDHocSinh(usernameCookie,namHoc.getIdNamHoc() );

        modelMap.addAttribute("buoi", buoiThuTietDAO.getAllBuoi());
        modelMap.addAttribute("thu", buoiThuTietDAO.getAllThu());
        modelMap.addAttribute("tiet", buoiThuTietDAO.getAllTiet());
        modelMap.addAttribute("monhoc", monHocDAO.getAllMonHoc());

        modelMap.addAttribute("hocky", hocKyDAO.getAllHocKyOfSemester(namHoc.getIdNamHoc()));
        modelMap.addAttribute("idhocky", Integer.parseInt(idHocKy));
        modelMap.addAttribute("thoikhoabieu", tkbDAO.layThoiKhoaBieu(String.valueOf(l.getIdLop()),idHocKy));
        return  "student/tkb";
    }

    //
    // GET [/student/profile] => Hiển thị trang thong tin hoc sinh
    @RequestMapping(value="/score", method = RequestMethod.GET)
    public String score(ModelMap modelMap,
                              @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                              HttpServletResponse response
    )  throws SQLException, ClassNotFoundException{

        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        HocSinhDAO hocSinhDAO = new HocSinhDAO();

        modelMap.addAttribute("student", hocSinhDAO.getHocSinhData(usernameCookie));

        return  "student/score";
    }
}
