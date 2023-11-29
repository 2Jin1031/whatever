package zkffl0.whatever.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zkffl0.whatever.dto.comment.CommentReqDto;
import zkffl0.whatever.dto.post.PostReqDto;
import zkffl0.whatever.service.CommentService;
import zkffl0.whatever.utils.swagger.account.RegisterReqApi;
import zkffl0.whatever.utils.swagger.account.RegisterResApi;

@Tag(name = "댓글", description = "댓글과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommnetController {

    private final CommentService commentService;

    @RegisterResApi
    @RegisterReqApi
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentReqDto commentReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED) // CommentMapping - HttpStatus.CREATED, GetMapping, PutMapping - HttpStatus.ok
                    .body(commentService.createComment(commentReqDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
