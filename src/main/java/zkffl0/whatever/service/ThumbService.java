package zkffl0.whatever.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import zkffl0.whatever.dto.thumb.ThumbResDto;
import zkffl0.whatever.repository.thumb.Thumb;
import zkffl0.whatever.repository.member.Member;
import zkffl0.whatever.dto.thumb.ThumbReqDto;
import zkffl0.whatever.repository.thumb.ThumbRepository;
import zkffl0.whatever.repository.member.MemberRepository;
import zkffl0.whatever.repository.post.Post;
import zkffl0.whatever.repository.post.PostRepository;

@Service
@AllArgsConstructor
@Slf4j
public class ThumbService {

    private final ThumbRepository thumbRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public ThumbResDto insert(ThumbReqDto thumbReqDto){

        Member member = memberRepository.findById(thumbReqDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id: " + thumbReqDto.getMemberId()));

        Post post = postRepository.findById(thumbReqDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found member id: " + thumbReqDto.getPostId()));

        if (thumbRepository.findByMemberAndPost(member, post).isPresent()) {
            new NotFoundException("Exception");
        }

        Thumb thumb = Thumb.builder()
                .post(post)
                .member(member)
                .build();

        thumbRepository.save(thumb);

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
                .orElseThrow(() -> new NotFoundException("Could not found member thumb id"));

        thumbRepository.delete(thumb);

        return "좋아요가 취소 되었습니다.";
    }
}
