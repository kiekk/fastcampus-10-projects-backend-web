package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}