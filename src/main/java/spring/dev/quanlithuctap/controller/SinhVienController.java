package spring.dev.quanlithuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.dev.quanlithuctap.entity.User;
import spring.dev.quanlithuctap.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    public String sinhvien(Model model){
        List<User> sinhviens = userRepository.findAll().stream().filter(user->user.getRole().getName().equals("ROLE_STUDENT")).toList();
        model.addAttribute("sinhviens", sinhviens);
        return "sinhvien/index";
    }

    @GetMapping("/edit/{id}")
    public String sinhvien(Model model, @PathVariable Integer id){
        User sinhvien = userRepository.findById(id).get();
        model.addAttribute("sinhvien", sinhvien);
        return "sinhvien/update";
    }

    @PostMapping("/edit/{id}")
    public String sinhvien(@ModelAttribute User sinhvien){
        User sinhVien1 = userRepository.findById(sinhvien.getId()).get();
        sinhvien.setRole(sinhVien1.getRole());
        sinhvien.setActive(sinhVien1.getActive());
        sinhvien.setPassword(sinhVien1.getPassword());
        userRepository.save(sinhvien);
        return "redirect:/sinhvien";
    }
}

