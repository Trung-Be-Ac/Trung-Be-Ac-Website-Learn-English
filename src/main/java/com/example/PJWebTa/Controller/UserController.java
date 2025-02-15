package com.example.PJWebTa.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PJWebTa.Model.Entity.User;
import com.example.PJWebTa.Model.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserRepo userRepo = new UserRepo();

    @GetMapping("/UserManager")
    public String AllUserManager(Model model) throws Exception {
        ArrayList<User> allUser = userRepo.showAllUsers();
        model.addAttribute("AllUser", allUser);
        return "User/UserManager";
    }

    // View Detail Profile User
    @GetMapping("/UserProfile/{userID}")
    public String ViewDetailProfileUser(@PathVariable("userID") int userID, Model model) throws Exception {
        User user = userRepo.getUserbyID(userID);
        model.addAttribute("UserDetail", user);
        return "User/UserProfile";
    }

    // View Edit User Profile
    @GetMapping("/UserEdit/{userID}")
    public String EditUser(@PathVariable("userID") int userID, Model model) throws Exception {
        User user = userRepo.getUserbyID(userID);
        model.addAttribute("UserEdit", user);
        return "UserEdit";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam("userName") String userName,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("userLevel") int userLevel,
            @RequestParam("userDateJoined") Date userDateJoined,
            @RequestParam("userRole") String userRole,
            @RequestParam("userType") int userType,
            @RequestParam("userPicture") String userPicture, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("LoginSuccess");
        userRepo.updateUsers(user.getUserID(), userName, userEmail, userLevel, userDateJoined, userRole, userType,
                userPicture);
        return "redirect:/UserProfile";

    }

    

}
