package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.article.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
