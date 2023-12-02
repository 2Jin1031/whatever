package zkffl0.whatever.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import zkffl0.whatever.dto.comment.CommentReqDto;
import zkffl0.whatever.dto.comment.CommentResDto;
import zkffl0.whatever.repository.comment.Comment;
import zkffl0.whatever.repository.comment.CommentRepository;
import zkffl0.whatever.repository.post.Post;
import zkffl0.whatever.repository.post.PostRepository;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    // TODO: post id 추가했으니 comment랑 연결
    public CommentResDto createComment(Long postId, CommentReqDto commentReqDto) throws NotFoundException{
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(postId + "에 해당하는 글이 존재하지 않습니다."));
        Comment comment = Comment.builder()
                .content(commentReqDto.getContent())
                .post(post)
                .build();

        commentRepository.save(comment);
        return CommentResDto.builder()
                .content(commentReqDto.getContent())
                .build();
    }

    public String updateComment(Long commentId, CommentReqDto commentReqDto) throws NotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(commentId + "에 해당하는 글이 존재하지 않습니다."));

        if (commentReqDto.getContent() != null) {
            comment.setContent(commentReqDto.getContent());
        }

        commentRepository.save(comment);
        return "업데이트에 성공하였습니다.";
    }

    public String deleteComment(Long commentId) throws NotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(commentId + "에 해당하는 글이 존재하지 않습니다."));


        commentRepository.deleteById(commentId);
        return "글이 삭제되었습니다.";
    }
}
