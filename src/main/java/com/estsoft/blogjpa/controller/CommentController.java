package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.AddCommentRequest;
import com.estsoft.blogjpa.dto.ArticleAndCommentResponse;
import com.estsoft.blogjpa.dto.CommentResponse;
import com.estsoft.blogjpa.model.Comment;
import com.estsoft.blogjpa.service.BlogService;
import com.estsoft.blogjpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{articleId}/{commentId}")
    public ResponseEntity<CommentResponse> showOneComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId, articleId);
        return ResponseEntity.ok(comment.toResponse());
    }

    @PostMapping("{articleId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long articleId,
                                                      @RequestBody AddCommentRequest request){
        Comment comment = commentService.save(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment.toResponse());
    }

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleAndCommentResponse> showArticleAndComment(@PathVariable Long articleId){
        ArticleAndCommentResponse article = commentService.findByArticle(articleId);
        return ResponseEntity.ok(article);
    }
}
