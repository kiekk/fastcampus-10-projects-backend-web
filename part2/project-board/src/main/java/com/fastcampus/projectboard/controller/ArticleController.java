package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.domain.constant.FormStatus;
import com.fastcampus.projectboard.domain.constant.SearchType;
import com.fastcampus.projectboard.dto.request.ArticleRequest;
import com.fastcampus.projectboard.dto.security.BoardPrincipal;
import com.fastcampus.projectboard.response.ArticleResponse;
import com.fastcampus.projectboard.response.ArticleWithCommentsResponse;
import com.fastcampus.projectboard.service.ArticleService;
import com.fastcampus.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        model.addAttribute("searchTypeHashtag", SearchType.HASHTAG);
        return "articles/index";
    }

    @GetMapping("{articleId}")
    public String article(@PathVariable Long articleId, ModelMap model) {
        ArticleWithCommentsResponse articleWithCommentsResponse = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));
        model.addAttribute("article", articleWithCommentsResponse);
        model.addAttribute("articleComments", articleWithCommentsResponse.articleCommentResponses());
        model.addAttribute("totalCount", articleService.getArticleCount());
        model.addAttribute("searchTypeHashtag", SearchType.HASHTAG);
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
    public String postNewArticle(ArticleRequest articleRequest,
                                 @AuthenticationPrincipal BoardPrincipal boardPrincipal) {
        articleService.saveArticle(articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles";
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable Long articleId, ModelMap map) {
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("formStatus", FormStatus.UPDATE);

        return "articles/form";
    }

    @PostMapping("/{articleId}/form")
    public String updateArticle(@PathVariable Long articleId, ArticleRequest articleRequest,
                                @AuthenticationPrincipal BoardPrincipal boardPrincipal) {
        articleService.updateArticle(articleId, articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleId,
                                @AuthenticationPrincipal BoardPrincipal boardPrincipal) {
        articleService.deleteArticle(articleId, boardPrincipal.getUsername());

        return "redirect:/articles";
    }

}
