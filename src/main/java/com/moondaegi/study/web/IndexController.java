package com.moondaegi.study.web;

import com.moondaegi.study.config.auth.SessionUser;
import com.moondaegi.study.service.posts.PostsService;
import com.moondaegi.study.web.dto.PostsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {  // 서버 탬플릿 엔진에서 사용하는 객체를 저장 할 수 있음
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        if (sessionUser != null) {
            model.addAttribute("userName", sessionUser.getName());
        }

        return "index";  // mustache로 index.mustache로 자동 저장된다
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostsResponseDTO postsResponseDTO = postsService.findById(id);
        model.addAttribute("post", postsResponseDTO);

        return "posts-update";
    }

    @DeleteMapping("/api/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);

        return id;
    }
}
