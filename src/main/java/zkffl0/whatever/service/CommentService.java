package zkffl0.whatever.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zkffl0.whatever.dto.comment.CommentReqDto;
import zkffl0.whatever.dto.comment.CommentResDto;
import zkffl0.whatever.dto.post.PostReqDto;
import zkffl0.whatever.dto.post.PostResDto;
import zkffl0.whatever.repository.comment.Comment;
import zkffl0.whatever.repository.comment.CommentRepository;
import zkffl0.whatever.repository.post.Post;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResDto createComment(CommentReqDto commentReqDto) {
        Comment comment = Comment.builder()
                .content(commentReqDto.getContent())
                .build();

        commentRepository.save(comment);
        return CommentResDto.builder()
                .content(commentReqDto.getContent())
                .build();
    }
}
