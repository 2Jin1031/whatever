package zkffl0.whatever.API.repository.thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import zkffl0.whatever.API.repository.member.Member;
import zkffl0.whatever.API.repository.post.Post;

import java.util.Optional;

public interface ThumbRepository extends JpaRepository<Thumb, Long> {

    Optional<Thumb> findByMemberAndPost(Member member, Post post);
    int countByPost(Post post);
}
