package com.lab.lab9.controllers;
import com.lab.lab9.dao.accountDAO;

import com.lab.lab9.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("")
public class AccountController {
    private accountDAO accountDAO;
    // GET [/login]
    // Hiển thị giao diện trang login
    // https://www.javainuse.com/spring/springboot_session
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(ModelMap modelMap,
                               @CookieValue(value = "username",
                                       defaultValue = "") String usernameCookie,
                               HttpServletRequest request){
//        System.out.print(usernameCookie);
//        if(usernameCookie.equals("")){
////            modelMap.addAttribute("error", "Please Login!");
////            modelMap.addAttribute("class_error", "p-3");
//            return "redirect:/login";
//        }
        System.out.print("Session Data => ");
//        List<String> messages = (List<String>) request.getSession().getAttribute("username_session");
//        for(String model : messages) {
//            System.out.println(model);
//        }
        return  "home";
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
        if(username.equals("") || password.equals("")){
            modelMap.addAttribute("error", "Missing data");
            modelMap.addAttribute("class_error", "p-3");
            return "login";
        }
        accountDAO = new accountDAO();
        // kiểm tra thôg tin có hợp lệ hay không?
        boolean isValid = accountDAO.checkUserValid(username, password);

        if(!isValid){
            modelMap.addAttribute("error", "Invalid Username or Password");
            modelMap.addAttribute("class_error", "p-3");

            return "login";
        }
        Account account = accountDAO.getUserData(username, password);
        System.out.print(account.toString());
        // create a cookie
        Cookie cookie = new Cookie("username", account.getUsername());
        response.addCookie(cookie);
        session.setAttribute("username_session", account.getUsername());
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
