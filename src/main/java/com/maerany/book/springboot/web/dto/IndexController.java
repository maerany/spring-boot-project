package com.maerany.book.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        /*
        *  mustache 스타터로 앞의 경로와 뒤의 확장자는 자동으로 지정됨
        *  src/main/resources/template/ + "index" + .mustache
        *  위로 변환된 String을 View Resolver가 처리한다.
        */
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
