package zkffl0.whatever.API.repository.post;

public interface PostRepositoryCustom {
    void updateThumbCnt(Post post, boolean isIncrement);
    void updateCommentCnt(Post post, boolean isIncrement);

    Post findByCommentId(Long commentId);
}
