package com.maerany.book.springboot.config;

import com.maerany.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver);
    }

    /*
        HandlerMethodArgumentResolver은 항상 WebMvcConfigurer의 AddArgumentResolvers()를 통해 추가해야 함
        다른 HandlerMethodArgumentResolver가 필요하다면 이와 같은 방식으로 추가해주면 됨.
     */
}
