package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.model.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleAndCommentResponse {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;

    public ArticleAndCommentResponse(Article article) {
        articleId = article.getId();
        title = article.getTitle();
        content = article.getContent();
        createdAt = article.getCreatedAt();
        updatedAt = article.getUpdatedAt();
        comments = article.getComments().stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
