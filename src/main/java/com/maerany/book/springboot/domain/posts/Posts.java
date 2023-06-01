package com.maerany.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    // 해당 class는 Entity 클래스임
    // Entity 클래스와 Response/Request 클래스와는 달리 사용되어야 됨 (용도가 다름)
    // Entity 클래는 DB와 맞닿은 핵심 class
    // Entity 클래스를 기준으로 table이 생성되고, schema가 변경된다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable =false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
