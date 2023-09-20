package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.domain.constant.FormStatus;
import com.fastcampus.projectboard.domain.constant.SearchType;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleRequest;
import com.fastcampus.projectboard.response.ArticleResponse;
import com.fastcampus.projectboard.response.ArticleWithCommentResponse;
import com.fastcampus.projectboard.service.ArticleService;
import com.fastcampus.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap model) {

        Page<ArticleResponse> articleResponses = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articleResponses.getTotalPages());
        model.addAttribute("articles", articleResponses);
        model.addAttribute("searchTypes", SearchType.values());
        model.addAttribute("paginationBarNumbers", barNumbers);
        return "articles/index";
    }

    @GetMapping("{articleId}")
    public String article(@PathVariable Long articleId, ModelMap model) {
        ArticleWithCommentResponse articleWithCommentResponse = ArticleWithCommentResponse.from(articleService.getArticleWithComments(articleId));
        model.addAttribute("article", articleWithCommentResponse);
        model.addAttribute("articleComments", articleWithCommentResponse.articleCommentResponses());
        model.addAttribute("totalCount", articleService.getArticleCount());
        return "articles/detail";
    }

    @GetMapping("search-hashtag")
    public String searchArticleHashtag(@RequestParam(required = false) String searchValue,
                                @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                ModelMap model) {
        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        List<String> hashtags = articleService.getHashtags();
        model.addAttribute("articles", articles);
        model.addAttribute("searchType", SearchType.HASHTAG);
        model.addAttribute("hashtags", hashtags);
        model.addAttribute("paginationBarNumbers", barNumbers);
        return "articles/search-hashtag";
    }

    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);

        return "articles/form";
    }

    @PostMapping("/form")
    public String postNewArticle(ArticleRequest articleRequest) {
        // TODO: 인증 정보를 넣어줘야 한다.
        articleService.saveArticle(articleRequest.toDto(UserAccountDto.of(
                "soono", "asdf1234", "soono@mail.com", "soono", "memo"
        )));

        return "redirect:/articles";
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable Long articleId, ModelMap map) {
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("formStatus", FormStatus.UPDATE);

        return "articles/form";
    }

    @PostMapping ("/{articleId}/form")
    public String updateArticle(@PathVariable Long articleId, ArticleRequest articleRequest) {
        // TODO: 인증 정보를 넣어줘야 한다.
        articleService.updateArticle(articleId, articleRequest.toDto(UserAccountDto.of(
                "soono", "asdf1234", "soono@mail.com", "soono", "memo"
        )));

        return "redirect:/articles/" + articleId;
    }

    @PostMapping ("/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleId) {
        // TODO: 인증 정보를 넣어줘야 한다.
        articleService.deleteArticle(articleId);

        return "redirect:/articles";
    }

}
