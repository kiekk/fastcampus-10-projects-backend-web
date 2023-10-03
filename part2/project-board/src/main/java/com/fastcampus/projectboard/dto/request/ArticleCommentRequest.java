package com.fastcampus.projectboard.dto.request;

import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.UserAccountDto;

public record ArticleCommentRequest(
        Long articleId,
        String content,
        Long parentCommentId
) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return ArticleCommentRequest.of(articleId, content, null);
    }

    public static ArticleCommentRequest of(Long articleId, String content, Long parentCommentId) {
        return new ArticleCommentRequest(articleId, content, parentCommentId);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }

}
