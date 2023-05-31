package com.maerany.book.springboot.web;


import com.maerany.book.springboot.service.posts.PostsService;
import com.maerany.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    //final이 선언된 필드를 인자값으로 하는 생성자를 만들어주는 annotation
    // : @RequiredArgsConstructor
    // 해당 annotation을 사용함으로써 얻은 이점
    // : 해당 class의 의존성 관계가 변경될 때마다 생성자 코드를 수정하는 번거로움이 줄어든다.
    private final PostsService postsService;

    @PostMapping("api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
