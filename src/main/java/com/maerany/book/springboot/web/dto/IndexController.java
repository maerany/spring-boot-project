package com.maerany.book.springboot.web.dto;

import com.maerany.book.springboot.config.auth.LoginUser;
import com.maerany.book.springboot.config.auth.dto.SessionUser;
import com.maerany.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
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


        /*
            @LoginUser Annotation을 이용함으로써 해당 코드 중복 제거 및 유지보수성 높힘
            // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장
               SessionUser user = (SessionUser) httpSession.getAttribute("user");

           - @LoginUser를 사용하면 어느 컨트롤러든지 해당 어노테이션을 통해 세션 정보를 가져올 수 있다.
        */
        // Session에 저장된 값이 있을 경우에만 model에 userName을 등록
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

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
