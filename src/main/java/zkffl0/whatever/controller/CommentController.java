package zkffl0.whatever.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zkffl0.whatever.dto.comment.CommentReqDto;
import zkffl0.whatever.service.CommentService;
import zkffl0.whatever.utils.swagger.account.RegisterReqApi;
import zkffl0.whatever.utils.swagger.account.RegisterResApi;

@Tag(name = "댓글", description = "댓글과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @RegisterResApi
    @RegisterReqApi
    @PostMapping("/create/{id}")
    public ResponseEntity<?> createComment(@PathVariable("id") Long id, @RequestBody CommentReqDto commentReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED) // CommentMapping - HttpStatus.CREATED, GetMapping, PutMapping - HttpStatus.ok
                    .body(commentService.createComment(id, commentReqDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody CommentReqDto commentReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.updateComment(id, commentReqDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.deleteComment(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
