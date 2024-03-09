package zkffl0.whatever.API.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import zkffl0.whatever.API.repository.post.Post;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findByPost(Post post);
}
