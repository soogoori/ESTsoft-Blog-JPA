package com.estsoft.blogjpa.model;

import com.estsoft.blogjpa.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "body", nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Comment(String body, Article article) {
        this.body=body;
        this.article=article;
    }

    public Comment() {}

    public CommentResponse toResponse() {
        return CommentResponse.builder()
                .id(id)
                .body(body)
                .createdAt(createdAt)
                .build();
    }
    public void update(String body) {
        this.body = body;
    }
}
