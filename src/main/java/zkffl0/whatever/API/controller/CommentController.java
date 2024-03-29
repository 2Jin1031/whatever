package zkffl0.whatever.API.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zkffl0.whatever.API.dto.comment.CommentReqDto;
import zkffl0.whatever.API.service.CommentService;
import zkffl0.whatever.API.utils.swagger.account.RegisterReqApi;
import zkffl0.whatever.API.utils.swagger.account.RegisterResApi;

@Tag(name = "댓글", description = "댓글과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @RegisterResApi
    @RegisterReqApi
    @PostMapping("/{post_id}")
    public ResponseEntity<?> createComment(@PathVariable("post_id") Long id, @RequestBody CommentReqDto commentReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED) // CommentMapping - HttpStatus.CREATED, GetMapping, PutMapping - HttpStatus.ok
                    .body(commentService.createComment(id, commentReqDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{comment_id}")
    public ResponseEntity<?> updateComment(@PathVariable("comment_id") Long id, @RequestBody CommentReqDto commentReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.updateComment(id, commentReqDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment_id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.deleteComment(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
