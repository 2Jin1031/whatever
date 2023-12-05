package zkffl0.whatever.service;

import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import zkffl0.whatever.dto.PageInfo;
import zkffl0.whatever.dto.post.PostInfoPageResDto;
import zkffl0.whatever.dto.post.PostReqDto;
import zkffl0.whatever.dto.post.PostResDto;
import zkffl0.whatever.repository.comment.Comment;
import zkffl0.whatever.repository.comment.CommentRepository;
import zkffl0.whatever.repository.post.Post;
import zkffl0.whatever.repository.post.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostResDto createPost(PostReqDto postReqDto) {
        Post post = Post.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .useTime(postReqDto.getUseTime())
                .build();

        postRepository.save(post);
        return PostResDto.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .useTime(postReqDto.getUseTime())
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

    public String deletePost(Long id) throws NotFoundException {
        postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 글이 존재하지 않습니다."));


        postRepository.deleteById(id);
        return "글이 삭제되었습니다.";
    }

    public Optional<List<Comment>> inquireComments(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 글이 존재하지 않습니다."));

        Optional<List<Comment>> comments = commentRepository.findByPost(post);

        if(comments.isEmpty()) {
            throw new NotFoundException("댓글이 존재하지 않습니다.");
        }

        return comments;
    }

    public PostInfoPageResDto inquirePosts(int page, int size) {
        Page<Post> postPages = postRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "useTime"))); // useTime을 기준으로 내림차순 정렬

        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .pageSize(size)
                .totalPages(postPages.getTotalPages())
                .totalNumber(postPages.getTotalElements())
                .build();

        List<PostResDto> postInfos = postPages.getContent() //  getContent() : Spring Data JPA에서 페이징된 결과에서 현재 페이지의 데이터를 가져오는 메서드
                .stream().map(o->new PostResDto(o)).collect(Collectors.toList()); // 각각의 게시물(Post)을 해당 게시물 정보 전송 객체(PostResDto)로 변환, 변환된 PostResDto를 리스트로 수집

        return PostInfoPageResDto.builder()
                .postInfos(postInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
