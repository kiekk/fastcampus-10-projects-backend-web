package org.example.mvc.controller;

import org.example.mvc.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController implements org.example.mvc.controller.Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }
}