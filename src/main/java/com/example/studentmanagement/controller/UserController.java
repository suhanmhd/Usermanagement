package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.UserInfo;
import com.example.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@Controller
public class UserController {



    @Autowired
    private UserService userService;



    @GetMapping("/home")
    public String home(Authentication authentication,Model model){


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String username = authentication.getName();


        if (((Collection<?>) authorities).contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/adminhome";
        } else {

            Optional<UserInfo> users = userService.getUserdata(username);
            UserInfo user = users.orElse(null);


            model.addAttribute("user", user);


            return "home";
        }
    }
    @PostMapping("/user")
    public String createuser(@ModelAttribute UserInfo userInfo,Model model){
        userInfo.setRoles("ROLE_USER");
        String result = userService.addUser(userInfo,model);
        return  result;
    }

    @GetMapping("/signup")
    public String signup(){

        return "signup";
    }


}
