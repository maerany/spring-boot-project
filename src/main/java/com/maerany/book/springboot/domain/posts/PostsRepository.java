package com.maerany.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{

    // SpringDataJpa에서 제공하지 않는 메소드는 아래와 같이 @Qyery로 작성할 수 있음
    // 실제로 아래 쿼리는 SpringDataJpa에서 제공하는 메소드가 있지만,
    // 가독성 측면에서 @Query를 사용하기도 함.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
