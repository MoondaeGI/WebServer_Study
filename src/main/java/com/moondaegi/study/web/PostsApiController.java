package com.moondaegi.study.web;

import com.moondaegi.study.service.posts.PostsService;
import com.moondaegi.study.web.dto.PostsResponseDTO;
import com.moondaegi.study.web.dto.PostsSaveRequestDTO;
import com.moondaegi.study.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
    // @Autowired를 사용하지 않고 생성자를 만들어 bean 객체를 받는다
    // 롬북 어노테이션(@RequiredArgsConstructor)은 final 선언된 모든 객체의 생성자를 만들어준다

    @PostMapping("/api/posts")
    public Long save(@RequestBody PostsSaveRequestDTO postsSaveRequestDTO) {
        return postsService.save(postsSaveRequestDTO);
    }

    @PutMapping("api/posts/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody PostsUpdateRequestDTO postsUpdateRequestDTO) {
        return postsService.update(id, postsUpdateRequestDTO);
    }

    @GetMapping("api/posts/{id}")
    public PostsResponseDTO findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
