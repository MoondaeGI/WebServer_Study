package com.moondaegi.study.web.dto;

import com.moondaegi.study.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsResponseDTO {
    private long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsResponseDTO(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
