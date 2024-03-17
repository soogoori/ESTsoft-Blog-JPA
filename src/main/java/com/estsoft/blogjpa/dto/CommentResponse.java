package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.model.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private String body;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment){
        id = comment.getId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt();
    }
}
