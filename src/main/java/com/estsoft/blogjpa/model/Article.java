package com.estsoft.blogjpa.model;

import com.estsoft.blogjpa.dto.ArticleAndCommentResponse;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.ArticleViewResponse;
import com.estsoft.blogjpa.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "article", cascade =CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article() {
    }

    public ArticleResponse toResponse() {
        return ArticleResponse.builder()
                .title(title)
                .content(content)
                .build();
    }

    public ArticleViewResponse toViewResponse() {
        return new ArticleViewResponse(id, title, content, createdAt, updatedAt);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
