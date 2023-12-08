package zkffl0.whatever.repository.post;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final EntityManager entityManager;

    public PostRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void updateThumbCnt(Post post, boolean isIncrement) {
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
}
