package com.example.register.services;

import com.example.register.models.Article;
import com.example.register.models.Comment;
import com.example.register.models.User;
import com.example.register.repositories.ArticleRepository;
import com.example.register.repositories.CommentRepository;
import com.example.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Comment> getCommentsByArticle(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public Comment addComment(String username, Long articleId, String content) {

        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }


        if (content == null || content.trim().isEmpty()) {
            throw new RuntimeException("Content cannot be empty");
        }


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));


        Comment comment = new Comment(user, article, content);
        return commentRepository.save(comment);
    }
}
