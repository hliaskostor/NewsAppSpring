package com.example.register.rest_controllers;

import com.example.register.models.Comment;
import com.example.register.models.CommentRequest;
import com.example.register.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{articleId}")
    public List<Comment> getCommentsForArticle(@PathVariable Long articleId) {
        return commentService.getCommentsByArticle(articleId);
    }

    @PostMapping("/user/add")
    public ResponseEntity<Comment> addComment(@RequestBody @Valid CommentRequest request) {

        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        try {
            Comment comment = commentService.addComment(request.getUsername(), request.getArticleId(), request.getContent());
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
