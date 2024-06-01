package com.system.Fashionhive.controller;

import com.system.Fashionhive.pojo.UserPojo;
import com.system.Fashionhive.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String getPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new UserPojo());
            return "profile-creation";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.saveUser(userPojo);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getProfilePage(Principal principal, Model model){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "profile";
    }

    @PostMapping("/saveProfile")
    public String getProfileEditPage(Principal principal, Model model){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication){
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "profile-creation";
    }
}
