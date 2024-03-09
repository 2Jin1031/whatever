package zkffl0.whatever.API.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import zkffl0.whatever.API.dto.thumb.ThumbResDto;
import zkffl0.whatever.API.repository.post.Post;
import zkffl0.whatever.API.repository.thumb.ThumbRepository;
import zkffl0.whatever.API.repository.thumb.Thumb;
import zkffl0.whatever.API.repository.member.Member;
import zkffl0.whatever.API.dto.thumb.ThumbReqDto;
import zkffl0.whatever.API.repository.member.MemberRepository;
import zkffl0.whatever.API.repository.post.PostRepository;

@Service
@AllArgsConstructor
@Slf4j
public class ThumbService {

    private final ThumbRepository thumbRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public ThumbResDto insert(ThumbReqDto thumbReqDto) {

        Member member = memberRepository.findById(thumbReqDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not find member with id: " + thumbReqDto.getMemberId()));

        Post post = postRepository.findById(thumbReqDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not find post with id: " + thumbReqDto.getPostId()));

        if (thumbRepository.findByMemberAndPost(member, post).isPresent()) {
            log.warn("Thumb already exists for member {} and post {}", member.getId(), post.getId());
            // 이미 좋아요가 존재하는 경우, 예외를 던지거나 로깅할 수 있습니다.
            // 여기서는 로깅만 하고 더 이상의 동작은 하지 않습니다.
            return ThumbResDto.builder()
                    .postId(post.getId())
                    .memberId(member.getId())
                    .build();
        }

        Thumb thumb = Thumb.builder()
                .post(post)
                .member(member)
                .build();

        thumbRepository.save(thumb);
        postRepository.updateThumbCnt(post, true);

        return ThumbResDto.builder()
                .postId(post.getId())
                .memberId(member.getId())
                .build();
    }


    @Transactional
    public String delete(ThumbReqDto thumbReqDto) {

        Member member = memberRepository.findById(thumbReqDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id: " + thumbReqDto.getMemberId()));

        Post post = postRepository.findById(thumbReqDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found member id: " + thumbReqDto.getPostId()));

        Thumb thumb = thumbRepository.findByMemberAndPost(member, post)
                .stream().findFirst()
                .orElseThrow(() -> new NotFoundException("Could not found member thumb id"));


        // TODO : 희찬 질문 로직
        postRepository.updateThumbCnt(post, false);
        thumbRepository.delete(thumb);

        return "좋아요가 취소 되었습니다.";
    }

    private void updateThumbCount(Post post) {
        int thumbCount = thumbRepository.countByPost(post);
        post.setThumbCnt(thumbCount);
        postRepository.save(post);
    }
}
