package zkffl0.whatever.repository.post;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final EntityManager entityManager;

    public PostRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void updateThumbCnt(Post post, boolean isIncrement) {

        if (post == null || post.getId() == null) {
            throw new IllegalArgumentException("Post or its ID cannot be null");
        }

        String operator = isIncrement ? "+" : "-";

        String jpql = "UPDATE Post p SET p.thumbCnt = p.thumbCnt " + operator + " 1 WHERE p.id = :postId";

        int updatedCount = entityManager.createQuery(jpql)
                .setParameter("postId", post.getId())
                .executeUpdate();

        if (updatedCount != 1) {
            // Handle the case where the update did not affect exactly one row (optional)
            // This could happen if the post with the given ID does not exist
            throw new RuntimeException("Failed to update thumb count for post with ID: " + post.getId());
        }
    }

    @Override
    @Transactional
    public void updateCommentCnt(Post post, boolean isIncrement) {

        if (post == null || post.getId() == null) {
            throw new IllegalArgumentException("Post or its ID cannot be null");
        }

        String operator = isIncrement ? "+" : "-";

        String jpql = "UPDATE Post p SET p.commentCnt = p.commentCnt " + operator + " 1 WHERE p.id = : postId";

        int updatedCount = entityManager.createQuery(jpql)
                .setParameter("postId", post.getId())
                .executeUpdate();

        if (updatedCount != 1) {
            throw new RuntimeException("Failed to update comment count for post with ID: " + post.getId());
        }
    }

    @Override
    @Transactional
    public Post findByCommentId(Long commentId) {
        String jpql = "SELECT c.post FROM Comment c WHERE c.id = :commentId";

        try {
            Post result = entityManager.createQuery(jpql, Post.class)
                    .setParameter("commentId", commentId)
                    .getSingleResult();

            return result;
        } catch (NoResultException e) {
            // Handle the case where no post is found for the given commentId
            return null; // or throw an exception
        } catch (NonUniqueResultException e) {
            // Handle the case where multiple posts are found for the given commentId
            throw new RuntimeException("Multiple posts found for comment with ID: " + commentId);
        }
    }
}
