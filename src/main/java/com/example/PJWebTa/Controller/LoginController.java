package com.example.PJWebTa.Controller;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.PJWebTa.Model.Repository.LoginRepo;
import com.example.PJWebTa.Model.Repository.UserRepo;

@Controller
public class LoginController {
    @Autowired
    UserRepo userRepo = new UserRepo();
    LoginRepo loginRepo = new LoginRepo();

    // LOGIN PAGE
    @GetMap
}
