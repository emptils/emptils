package com.emptils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return "dddddddddddddddd";
    }
}
