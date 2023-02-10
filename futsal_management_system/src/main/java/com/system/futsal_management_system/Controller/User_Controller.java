package com.system.futsal_management_system.Controller;

import com.system.futsal_management_system.Pojo.UserPojo;
import com.system.futsal_management_system.Service.UserService;
import com.system.futsal_management_system.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class User_Controller {
    private final UserService userService;

    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserPojo());

        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home/homepage";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/user/login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "editprofile";
    }

    @GetMapping("/profile/{id}")
    public String getUserProfiile(@PathVariable("id") Integer id, Model model){
        User user = userService.fetchById(id);
        model.addAttribute("users", new UserPojo(user));
        model.addAttribute("currentUser", user);

        return "editprofile";
    }


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user =userService.fetchById(id);
        model.addAttribute("currentUser", new UserPojo(user));
        return "redirect:/home/homepage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/user/login";
    }


    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model){
        model.addAttribute("users",new UserPojo());
        return ("forgetpassword");
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid UserPojo userPojo){
        userService.processPasswordResetRequest(userPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/user/login";
    }
}







