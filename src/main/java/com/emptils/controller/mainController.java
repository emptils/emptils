package com.emptils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping

public class mainController {

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test() {
        return "dddddddddddddddd";
    }
}
