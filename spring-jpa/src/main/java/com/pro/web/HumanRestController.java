package com.pro.web;

import com.pro.model.Human;
import com.pro.persistence.HumanRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HumanRestController {

    private HumanRepository humanRepository;

    HumanRestController(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @RequestMapping(value = "humanDefault")
    public Collection<Human> getHumansDefault() {
        return this.humanRepository.findAll();
    }
}
