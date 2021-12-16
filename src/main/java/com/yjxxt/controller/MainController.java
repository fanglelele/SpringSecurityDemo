package com.yjxxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("toMain")
    public String toMain(){
        return "redirect:/main.html";
    }

    @RequestMapping("toError")
    public String toError(){
        return "redirect:error.html";
    }
}
