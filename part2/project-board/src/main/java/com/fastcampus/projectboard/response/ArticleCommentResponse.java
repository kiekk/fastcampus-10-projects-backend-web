package com.fastcampus.projectboard.response;

import com.fastcampus.projectboard.dto.ArticleCommentDto;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname
) {

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleCommentResponse(id, content, createdAt, email, nickname);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (Strings.isEmpty(nickname)) {
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleCommentResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname
        );
    }

}