package com.lab.lab9.controllers;
import com.lab.lab9.dao.TaiKhoanDAO;

import com.lab.lab9.models.TaiKhoan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
        TaiKhoan taikhoan = accountDAO.getUserData(username, password);
        // Role = "" => Tài khoản đăng nhập không đúng
        if(taikhoan.getRole().equals("")){
            modelMap.addAttribute("error", "Invalid Username or Password!");
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
        if(taikhoan.getRole().equals("STUDENT")){
            return "redirect:/student/home";
        }
        return "redirect:/";
    }
//    // POST [/]
//    @RequestMapping(value="", method = RequestMethod.POST)
//    public String addNewLaptop(ModelMap modelMap,
//                               @RequestParam(value = "name") String name,
//                               @RequestParam(value = "ram") int ram,
//                               @RequestParam(value = "price") int price,
//                               @RequestParam("image") MultipartFile image) throws SQLException, ClassNotFoundException, IOException {
//        String fileNameNew = GenerateId.getRandomString(10) + ".png";
//        UploadFile.saveFile(fileNameNew, image);
//        String error = "";
//        if(name.equals("")) error += "Laptop name can not be empty";
//        if(ram < 0) error += "Laptop RAM not valid ";
//        if(price < 0) error += "Laptop Price not valid";
//        if(!error.equals("")) {
//            modelMap.addAttribute("error", error);
//            return  "error";
//        }
//        try{
//            laptopDAO.addNewLaptop(name, ram, price, fileNameNew);
//        }catch(SQLException e){
//            System.out.print(e);
//        }
//        return "redirect:/";
//    }
//    // GET [/delete]
//    @RequestMapping(value="/delete", method = RequestMethod.GET)
//    public String deleteLaptop(@RequestParam(value = "id") int id){
//        try{
//            laptopDAO.deleteLaptop(id);
//        }catch(SQLException e){
//            System.out.print(e);
//        }
//        return "redirect:/";
//    }
//
//    // POST [/edit]
//    @RequestMapping(value="/edit", method = RequestMethod.POST)
//    public String editLaptop(ModelMap modelMap,
//                             @RequestParam(value = "id") int id,
//                             @RequestParam(value = "name") String name,
//                             @RequestParam(value = "ram") int ram,
//                             @RequestParam(value = "price") int price){
//        String error = "";
//        if(name.equals("")) error += "Laptop name can not be empty";
//        if(ram < 0) error += "Laptop RAM not valid ";
//        if(price < 0) error += "Laptop Price not valid";
//        if(!error.equals("")) {
//            modelMap.addAttribute("error", error);
//            return  "error";
//        }
//
//        try{
//            laptopDAO.editLaptop(id, name, ram, price);
//        }catch(SQLException e){
//            System.out.print(e);
//        }
//        return "redirect:/";
//    }
}
