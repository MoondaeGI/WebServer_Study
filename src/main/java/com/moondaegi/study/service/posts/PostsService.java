package com.moondaegi.study.service.posts;

import com.moondaegi.study.domain.posts.Posts;
import com.moondaegi.study.domain.posts.PostsRepository;
import com.moondaegi.study.web.dto.PostsListResponseDTO;
import com.moondaegi.study.web.dto.PostsResponseDTO;
import com.moondaegi.study.web.dto.PostsSaveRequestDTO;
import com.moondaegi.study.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDTO postsSaveRequestDTO) {
        return postsRepository.save(postsSaveRequestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO postsUpdateRequestDTO) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(postsUpdateRequestDTO.getTitle(), postsUpdateRequestDTO.getContent());

        return id;
    }

    public PostsResponseDTO findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDTO(posts);
    }

    @Transactional
    public List<PostsListResponseDTO> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDTO::new)  // posts -> new PostsResponseDTO(posts)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(post);
    }
}
