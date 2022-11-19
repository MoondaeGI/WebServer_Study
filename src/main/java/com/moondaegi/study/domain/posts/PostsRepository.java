package com.moondaegi.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// Posts 클래스로 database를 접근하게 해줄 jpaRepository(DAO), 기본적인 CRUD 생성
// jparepository와 entity는 같은 pakage에 있어야 한다
// jparepository<entity, id type>
public interface PostsRepository extends JpaRepository<Posts, Long> {}
