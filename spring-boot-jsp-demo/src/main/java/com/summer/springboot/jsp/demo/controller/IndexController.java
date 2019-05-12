package com.summer.springboot.jsp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping({"", "/index"})
    public String index(Model model) {
        model.addAttribute("time", new Date());
        return "index";
    }

}
