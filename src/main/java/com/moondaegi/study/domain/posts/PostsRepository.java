package com.moondaegi.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Posts 클래스로 database를 접근하게 해줄 jpaRepository(DAO), 기본적인 CRUD 생성
// jparepository와 entity는 같은 pakage에 있어야 한다
// jparepository<entity, id type>
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")  // 직접 쿼리 작성으로 사용 가능
    List<Posts> findAllDesc();  // 내림차순으로 찾기
}
