package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.article.Article;
import com.fastcampus.projectboard.domain.article.ArticleComment;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

// TODO: 간단하게 CRUD 만 테스트 했기 때문에, 테스트 케이스 보완
@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {


    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsArticleComments() {
        // given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title", "content", "#spring")));

        // when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleComment_whenSavingComment_thenSavesArticleComment() {
        // given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Soono", LocalDateTime.now(), "Soono", "comment"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}
