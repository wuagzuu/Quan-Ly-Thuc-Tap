package spring.dev.quanlithuctap.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.dev.quanlithuctap.config.SpringSecurity;
import spring.dev.quanlithuctap.entity.User;
import spring.dev.quanlithuctap.repository.RoleRepository;
import spring.dev.quanlithuctap.repository.UserRepository;

import javax.swing.*;
import java.io.IOException;

@Controller
public class UserController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(Model model, Authentication authentication) throws IOException {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        } else {
            model.addAttribute("user", new User());
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) throws IOException {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) throws IOException {
        user.setRole(roleRepository.findByName("ROLE_TEACHER"));
        user.setActive(false);
        user.setPassword(SpringSecurity.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("notification", "Register successfully. Please log in");
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @PostMapping("/change-password")
    public String changePassword(Model model, Authentication authentication, @RequestParam String password, @RequestParam String newpassword, @RequestParam String renewpassword){
        User user = userRepository.findByEmail(authentication.getName());
        if(passwordEncoder.matches(password, user.getPassword())){
            if(newpassword.equals(renewpassword)){
                user.setPassword(passwordEncoder.encode(newpassword));
                user.setFirstLogin(false);
                userRepository.save(user);
                model.addAttribute("notification", "Change password successfully!");
            }
            else{
                model.addAttribute("error", "Password not matches!");
            }
        }
        else{
            model.addAttribute("error", "Wrong password");
        }
        model.addAttribute("firstLogin", true);
        return "profile";
    }
}
