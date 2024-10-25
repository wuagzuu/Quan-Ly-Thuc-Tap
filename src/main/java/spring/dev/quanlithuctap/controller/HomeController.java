package spring.dev.quanlithuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.dev.quanlithuctap.entity.User;
import spring.dev.quanlithuctap.repository.UserRepository;

import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/home")
    public String home(Model model, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        if(user.getFirstLogin() != null && user.getFirstLogin()){
            model.addAttribute("firstLogin", true);
            return "profile";
        }
        return "index";
    }
}
