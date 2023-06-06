package com.maerany.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

    /*
        1. @Target(ElementType.PARAMETER)
        - 해당 어노테이션이 생성될 수 있는 위치 생성
        - PARAMETER로 지정했으므로 메소드의 파라미터로 선언된 객체에서만 사용
        - 이 외에도 class 선언문에 쓸 수 있는 Type 등이 있음

        2. @interface
        - 해당 파일을 어노테이션 클래스로 지정
        - LoginUser라는 이름을 가진 어노테이션이 생성되었다고 볼 수 있음
     */

}


