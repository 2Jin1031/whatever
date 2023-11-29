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

    public String updateComment(Long id, CommentReqDto commentReqDto) throws NotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 글이 존재하지 않습니다."));

        if (commentReqDto.getContent() != null) {
            comment.setContent(commentReqDto.getContent());
        }

        commentRepository.save(comment);
        return "업데이트에 성공하였습니다.";
    }

    public String deleteComment(Long id) throws NotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 글이 존재하지 않습니다."));


        commentRepository.deleteById(id);
        return "글이 삭제되었습니다.";
    }
}
