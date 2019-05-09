package com.pro.web;

import com.pro.persistence.HumanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HumanOldController {

    private HumanRepository humanRepository;

    HumanOldController(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @RequestMapping(value = "humanModel")
    public String getHumansModel(Model model) {
        model.addAttribute("humans", humanRepository.getAllByName("Taras"));
        return "human";
    }
}
