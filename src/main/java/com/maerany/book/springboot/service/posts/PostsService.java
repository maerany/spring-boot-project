package com.maerany.book.springboot.service.posts;

import com.maerany.book.springboot.domain.posts.Posts;
import com.maerany.book.springboot.domain.posts.PostsRepository;
import com.maerany.book.springboot.web.dto.PostsResponseDto;
import com.maerany.book.springboot.web.dto.PostsSaveRequestDto;
import com.maerany.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        /*
        * update 쿼리에서 DB에 Query를 날리는 부분이 없음
        * 이런 구조가 가능한 이유는 "JPA(java persistence API)의 영속성 컨텍스트"때문
        *   # 영속성 컨텍스트?
        *     Entity를 영구 저장하는 환경, 일종의 논리적 개념
        *     JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함이 되어 있는가 아닌가로 나뉘어짐
        *     JPA의 EntityManager가 활성화된 상태로(Spring Data JPA 기본 옵션)
        *     트랜잭션 안에서 DB의 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
        *     이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영함
        *     즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날리지 않아도 DB에 변경된 값이 저장됨
        *      ==> 해당 개념을 더티 체킹(dirty checking)이라고 함
        */
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

}
