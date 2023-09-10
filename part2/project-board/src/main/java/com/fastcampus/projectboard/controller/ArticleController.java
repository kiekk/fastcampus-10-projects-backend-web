package com.fastcampus.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("")
    public String articles(ModelMap model) {
        model.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("{articleId}")
    public String article(@PathVariable Long articleId, ModelMap model) {
        model.addAttribute("article", "article"); // TODO: 구현할 때 여기에 실제 데이터를 넣어줘야 함
        model.addAttribute("articleComments", List.of());
        return "articles/detail";
    }

}
