package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.dto.AddCommentRequest;
import com.estsoft.blogjpa.dto.ArticleAndCommentResponse;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.model.Comment;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.estsoft.blogjpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public Comment save(Long id, AddCommentRequest request){
        Article article =blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));

        Comment comment = request.toEntity(article);
        return commentRepository.save(comment);
    }

    public Comment findById(Long id, Long articleId){
        Article article=blogRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 조회 실패: 해당 게시글이 존재하지 않습니다. " + articleId));

        return commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 조회 실패: 해당 댓글이 존재하지 않습니다."));
    }

    public ArticleAndCommentResponse findByArticle(Long id){
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        return new ArticleAndCommentResponse((article));
    }
}
