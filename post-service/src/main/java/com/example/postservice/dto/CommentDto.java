package com.example.postservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDto {

    public CommentDto() {
    }

    public CommentDto(String uuid, String postUuid, String author, String content, int votes) {
        this.uuid = uuid;
        this.postUuid = postUuid;
        this.author = author;
        this.content = content;
        this.votes = votes;
    }

    private String uuid;

    @JsonProperty("post-uuid")
    private String postUuid;

    private String author;

    private String content;

    private int votes;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPostUuid() {
        return postUuid;
    }

    public void setPostUuid(String postUuid) {
        this.postUuid = postUuid;
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

}
