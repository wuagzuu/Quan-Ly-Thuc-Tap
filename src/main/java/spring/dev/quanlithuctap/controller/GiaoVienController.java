package spring.dev.quanlithuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.dev.quanlithuctap.entity.User;
import spring.dev.quanlithuctap.repository.UserRepository;

import java.util.List;

@RequestMapping("/giaovien")
@Controller
public class GiaoVienController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    public String giaovien(Model model){
        List<User> giaoviens = userRepository.findAll().stream().filter(user -> user.getRole().getName().equals("ROLE_TEACHER")).toList();
        model.addAttribute("giaoviens", giaoviens);
        return "giaovien/index";
    }

    @GetMapping("/toggleActive/{id}")
    public String edit(@PathVariable Integer id){
        User user = userRepository.findById(id).get();
        user.setActive(!user.getActive());
        userRepository.save(user);
        return "redirect:/giaovien";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return "redirect:/giaovien";
    }
}
