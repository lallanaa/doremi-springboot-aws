package com.springbookaws.book.doremi.service.posts;

import com.springbookaws.book.doremi.domain.posts.Posts;
import com.springbookaws.book.doremi.domain.posts.PostsRepository;
import com.springbookaws.book.doremi.web.dto.PostResponseDto;
import com.springbookaws.book.doremi.web.dto.PostsUpdateRequestDto;
import com.springbookaws.book.doremi.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        return new PostResponseDto(entity);
    }
}
