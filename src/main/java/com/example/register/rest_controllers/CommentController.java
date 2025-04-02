package com.example.register.rest_controllers;

import com.example.register.models.Comment;
import com.example.register.models.CommentRequest;
import com.example.register.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Comment addComment(@RequestBody CommentRequest request) {
        return commentService.addComment(request.getUsername(), request.getArticleId(), request.getContent());
    }
}
