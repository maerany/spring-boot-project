package com.maerany.book.springboot.web.dto;

import com.maerany.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){
        /*
        *  mustache 스타터로 앞의 경로와 뒤의 확장자는 자동으로 지정됨
        *  src/main/resources/template/ + "index" + .mustache
        *  위로 변환된 String을 View Resolver가 처리한다.
        */

        /*
         * - model
         *  서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
         *  해당 메소드에서는 postsSerivce.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
         */
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }


}
