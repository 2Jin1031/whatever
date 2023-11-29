package zkffl0.whatever.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
