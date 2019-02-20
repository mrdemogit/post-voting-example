package com.example.postservice.dto;


import java.util.ArrayList;
import java.util.List;

public class PostDto {

    public PostDto() {
    }

    public PostDto(String uuid, String author, String content, int votes, List<CommentDto> comments) {
        this.uuid = uuid;
        this.author = author;
        this.content = content;
        this.votes = votes;
        this.comments = comments;
    }

    private String uuid;

    private String author;

    private String content;

    private int votes;

    private List<CommentDto> comments = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
