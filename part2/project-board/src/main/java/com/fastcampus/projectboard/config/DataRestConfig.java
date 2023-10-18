package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.domain.article.Article;
import com.fastcampus.projectboard.domain.article.ArticleComment;
import com.fastcampus.projectboard.domain.hashtag.Hashtag;
import com.fastcampus.projectboard.domain.user.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig((config, cors) ->
                config
                        .exposeIdsFor(UserAccount.class)
                        .exposeIdsFor(Article.class)
                        .exposeIdsFor(ArticleComment.class)
                        .exposeIdsFor(Hashtag.class)
        );
    }

}
