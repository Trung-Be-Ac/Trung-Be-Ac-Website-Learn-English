package com.example.PJWebTa.Controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.PJWebTa.Model.Repository.LoginRepo;
import com.example.PJWebTa.Model.Repository.UserRepo;
import com.example.PJWebTa.Model.Entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginSignupController {
    @Autowired
    UserRepo userRepo = new UserRepo();
    @Autowired
    LoginRepo loginRepo = new LoginRepo();

    // Login
    @GetMapping("/Login")
    public String Login() {
        return "User/Login";
    }

    @PostMapping("/inputLogin")
    public String inputLogin(@RequestParam("username") String userName, @RequestParam("password") String passWord,
            HttpSession httpSession)
            throws Exception {
        User user = loginRepo.CheckLogin(userName, passWord);
        if (user == null) {
            return "Public/Index";
        } else {
            httpSession.setAttribute("LoginSuccess", user);
            return ("Public/Index");
        }
    }

    // Logout
    @GetMapping("/Logout")
    public String logOut(HttpSession httpSession) {
        httpSession.removeAttribute("LoginSuccess");
        return "redirect:/Index";
    }

    // Signup
    @GetMapping("/Signup")
    public String signUp() {
        return "/User/Signup";
    }

    @PostMapping("/inputSignup")
    public String inputSignUp(@RequestParam("name") String name, @RequestParam("username") String userName,
            @RequestParam("password") String passWord,
            @RequestParam("cpassword") String cPassword, @RequestParam("email") String email,
            @RequestParam("role") String role) throws Exception {
        if (passWord.equals(cPassword)) {
            User user = new User(name, email, role, userName, passWord);
            userRepo.addUsers(user);
            return ("redirect:/Index");
        } else {
            return "User/Signup";
        }

    }

    // Change Password
    @GetMapping("/ChangePassword/{userID}")
    public String ChangePassword(Model model, @PathVariable("userID") int userID) throws Exception {
        User user = userRepo.getUserbyID(userID);
        model.addAttribute("ChangePassword", user);
        return "/User/ChangePassword";
    }
    @PostMapping("/changPassword")
    public String changPassword(
            @RequestParam("userID") int userID,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) throws Exception {
        User user = userRepo.getUserbyID(userID);
        String currentPassword = user.getPasswordA();

        if (!oldPassword.equals(currentPassword)) {
            model.addAttribute("WrongInput", "Mật khẩu cũ không đúng.");
            return "/User/ChangePassword";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("WrongInput", "Mật khẩu xác nhận không khớp.");
            return "/User/ChangePassword";
        }

        loginRepo.UpdatePassword(userID, newPassword);
        model.addAttribute("Success", "Thay đổi mật khẩu thành công.");
        return "redirect:/Index";
    }

}
