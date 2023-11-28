package zkffl0.whatever.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import zkffl0.whatever.dto.post.PostReqDto;
import zkffl0.whatever.dto.post.PostResDto;
import zkffl0.whatever.repository.post.Post;
import zkffl0.whatever.repository.post.PostRepository;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResDto createPost(PostReqDto postReqDto) {
        Post post = Post.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .build();

        postRepository.save(post);
        return PostResDto.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .build();
    }

    public String updatePost(Long id, PostReqDto postReqDto) throws NotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 글이 존재하지 않습니다."));

        if (postReqDto.getTitle() != null) {
            post.setTitle(postReqDto.getTitle());
        }
        if (postReqDto.getContent() != null) {
            post.setContent(postReqDto.getContent());
        }

        postRepository.save(post);
        return "업데이트에 성공하였습니다.";
    }
}
