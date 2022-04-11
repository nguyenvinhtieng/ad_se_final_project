package com.lab.lab9.controllers;
import com.lab.lab9.dao.laptopDAO;
import com.lab.lab9.models.Laptop;
import com.lab.lab9.utils.GenerateId;
import com.lab.lab9.utils.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("")
public class AccountController {
//    private laptopDAO laptopDAO;
    // Trang Chu => Hien thi tat ca laptop
    // GET [/]
    @RequestMapping(value="", method = RequestMethod.GET)
    public String showHome(ModelMap modelMap){
//        try{
//            laptopDAO = new laptopDAO();
//            modelMap.addAttribute("laptops", laptopDAO.getLaptops());
//        }catch(ClassNotFoundException | SQLException e){
//            System.out.print(e);
//            modelMap.addAttribute("laptops", new ArrayList<Laptop>());
//        }
        return  "home";
    }
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap modelMap){
//        try{
//            laptopDAO = new laptopDAO();
//            modelMap.addAttribute("laptops", laptopDAO.getLaptops());
//        }catch(ClassNotFoundException | SQLException e){
//            System.out.print(e);
//            modelMap.addAttribute("laptops", new ArrayList<Laptop>());
//        }
        return  "login";
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
