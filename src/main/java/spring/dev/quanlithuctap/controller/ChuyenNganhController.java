package spring.dev.quanlithuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.dev.quanlithuctap.entity.Major;
import spring.dev.quanlithuctap.repository.MajorRepository;

@Controller
@RequestMapping("/chuyennganh")
public class ChuyenNganhController {
    @Autowired
    private MajorRepository majorRepository;
    @GetMapping(value = {"/", ""})
    public String majors(Model model){
        model.addAttribute("majors", majorRepository.findAll());
        return "chuyennganh/index";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Major major = majorRepository.findById(id).get();
        model.addAttribute("major", major);
        return "chuyenngang/edit";
    }
}
