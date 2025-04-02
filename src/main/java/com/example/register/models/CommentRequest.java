package com.example.register.models;

public class CommentRequest {
    private String username;
    private Long articleId;
    private String content;

    public String getUsername() { return username; }
    public Long getArticleId() { return articleId; }
    public String getContent() { return content; }

    public void setUsername(String username) { this.username = username; }
    public void setArticleId(Long articleId) { this.articleId = articleId; }
    public void setContent(String content) { this.content = content; }
}
