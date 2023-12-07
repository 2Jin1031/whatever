package zkffl0.whatever.repository.thumb;
import zkffl0.whatever.repository.thumb.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import zkffl0.whatever.repository.member.Member;
import zkffl0.whatever.repository.post.Post;

import java.util.Optional;

public interface ThumbRepository extends JpaRepository<Thumb, Long> {

    Optional<Thumb> findByMemberAndPost(Member member, Post post);
}
