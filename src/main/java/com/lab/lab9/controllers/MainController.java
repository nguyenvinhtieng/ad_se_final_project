package com.lab.lab9.controllers;
import com.lab.lab9.dao.TaiKhoanDAO;

import com.lab.lab9.models.TaiKhoan;
import com.lab.lab9.utils.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("")
public class MainController {
    private TaiKhoanDAO accountDAO;

    // GET [/]
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(ModelMap modelMap){
        return  "redirect:/login";
    }

    // GET [/login]
    // Hiển thị giao diện trang login
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap modelMap){
//        modelMap.addAttribute("error", "Khong vo loi");
        return  "login";
    }

    // Logout
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(ModelMap modelMap){
//        modelMap.addAttribute("error", "Khong vo loi");

        // Xoa Cookie
        return  "redirect:/login";
    }
    // Logout
    @RequestMapping(value="/changepass", method = RequestMethod.POST)
    public String changepass(ModelMap modelMap,
                             @CookieValue(value = "username", defaultValue = "") String usernameCookie,
                             @RequestParam(value = "currp") String currp,
                             @RequestParam(value = "newp") String newp,
                             @RequestParam(value = "repeatp") String repeatp,
                             HttpServletRequest req,
                             HttpServletResponse resp) throws SQLException, IOException, ClassNotFoundException {
//        modelMap.addAttribute("error", "Khong vo loi");
        if(usernameCookie.equals("")){
            return "redirect:/login";
        }
        String toast = "";
        // validation
        if(!newp.equals(repeatp)){
            toast = "error#Repeat/password/not/correct!#Error!";
        }
        if(newp.length() < 8){
            toast = "error#Password/must/more/than/8/characters!#Error!";
        }
        accountDAO = new TaiKhoanDAO();
        TaiKhoan taikhoan = accountDAO.getUserData(usernameCookie);
        boolean validPass = Hashing.checkPassword(currp,taikhoan.getPassword());
        if(!validPass){
            toast = "error#Current/password/is/not/correct!#Error!";
        }
        if(!toast.equals("")){
            Cookie cookie_toast = new Cookie("toast_message", toast);
            cookie_toast.setPath(req.getContextPath());
            resp.addCookie(cookie_toast);
//            RequestDispatcher dd = request.getRequestDispatcher("error.jsp");
//
//            dd.forward(request, response);
//            resp.sendRedirect(req.getContextPath() + "/redirected");
//            return  "redirect:back";
        }
        System.out.println("URL: " + req.getContextPath());
        // Xoa Cookie
        return  "redirect:/login";
    }

    // POST [/login]
    // Xử lý user đăng nhập
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String addNewLaptop(ModelMap modelMap,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               HttpServletResponse response,
                               HttpSession session)
            throws SQLException, ClassNotFoundException, IOException {
        // Validation cơ bản
        if(username.equals("") || password.equals("")){
            modelMap.addAttribute("error", "Missing data");
            modelMap.addAttribute("class_error", "p-3");
            return "login";
        }

        accountDAO = new TaiKhoanDAO();

        // Lấy thông tin của user
        TaiKhoan taikhoan = accountDAO.getUserData(username);
        System.out.println("Pass" + taikhoan.getPassword());
        // Role = "" => Tài khoản đăng nhập không đúng
        if(taikhoan.getRole().equals("")){
            modelMap.addAttribute("error", "Invalid Username or Password!");
            modelMap.addAttribute("class_error", "p-3");
            return "login";
        }
        boolean validPass = Hashing.checkPassword(password,taikhoan.getPassword());
        if(!validPass){
            modelMap.addAttribute("error", "Password Wrong!");
            modelMap.addAttribute("class_error", "p-3");
            return "login";
        }
        // STATUS = "INACTIVE" => Tài khoản INACTIVE
        if(taikhoan.getStatus().equals("INACTIVE")){
            modelMap.addAttribute("error", "Account Inactive!");
            modelMap.addAttribute("class_error", "p-3");
            return "login";
        }

        // Tạo cookie => Có thể thêm bước mã hóa cookie sau này
        Cookie cookie_username = new Cookie("username", username);
        Cookie cookie_role = new Cookie("role", taikhoan.getRole());
        response.addCookie(cookie_username);
        response.addCookie(cookie_role);

        // Check role để điều hướng người dùng
        if(taikhoan.getRole().equals("ADMIN")){
            return "redirect:/admin/home";
        }
        if(taikhoan.getRole().equals("HOCSINH")){

            return "redirect:/student/home";
        }
        if(taikhoan.getRole().equals("GIAOVIEN")){
            return "redirect:/teacher/home";
        }
        return "redirect:/";
    }

}
