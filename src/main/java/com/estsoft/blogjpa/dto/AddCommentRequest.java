package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {

    private String body;

    public Comment toEntity(Article article){
        return Comment.builder()
                .body(body)
                .article(article)
                .build();
    }
}
