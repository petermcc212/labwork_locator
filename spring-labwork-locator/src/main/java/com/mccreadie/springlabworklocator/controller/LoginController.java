package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.User;
import com.mccreadie.springlabworklocator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String showLoginPage(){
        return "security/loginPage";
    }


    @RequestMapping("/forbidden")
    public String showForbiddenErrorPage() {
        return "403";
    }

    @RequestMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("user", new User());
        return ("security/form-signup");
    }

    @RequestMapping("/addReceptionist")
    public String addReceptionist(Model model)
    {
        model.addAttribute("user", new User());
        return ("security/form-signup");
    }

    @PostMapping("processSignup")
    public String processSignup(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        boolean errors = false;

        if(!user.getPassword().equals(user.getConfirmPassword())) {
            redirectAttributes.addAttribute("differentPasswords", "Passwords are different");
            errors = true;
        }

        if(userService.loginExists(user.getLogin())) {
            redirectAttributes.addAttribute("loginExists", "Login already exists in the database");
            errors = true;
        }

        if(errors) {
            return "redirect:/signup";
        }


        userService.createNewAccount(user);


        return "security/loginPage";
    }
}
