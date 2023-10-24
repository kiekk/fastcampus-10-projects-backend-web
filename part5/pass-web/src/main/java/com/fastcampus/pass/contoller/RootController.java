package com.fastcampus.pass.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping(value = {"", "/"})
    public String index() {
        return "redirect:/admin";
    }
}
