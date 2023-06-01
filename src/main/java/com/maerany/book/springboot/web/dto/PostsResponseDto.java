package com.maerany.book.springboot.web.dto;

import com.maerany.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    //PostsResponseDto는 Entity의 필드 중 일부만 사용하므로 생성자로 Entity를 받아 필드에 값을 넣는다.
    //모든 필드를 가진 생성자가 필요하지 않으므로 Dto는 Entity를 받아서 처리함.
    //응답을 하는 PostsResponseDto(조회기능)

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
