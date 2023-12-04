package zkffl0.whatever.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zkffl0.whatever.dto.post.PostReqDto;
import zkffl0.whatever.service.PostService;
import zkffl0.whatever.utils.swagger.account.RegisterReqApi;
import zkffl0.whatever.utils.swagger.account.RegisterResApi;

import javax.validation.constraints.Min;

@Tag(name = "글", description = "글과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    /**
     * 글 생성하기
     */
    @RegisterResApi
    @RegisterReqApi
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostReqDto postReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED) // PostMapping - HttpStatus.CREATED, GetMapping, PutMapping - HttpStatus.ok
                    .body(postService.createPost(postReqDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * 글 수정하기
     */
    @PutMapping("/{post_id}")
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long id, @RequestBody PostReqDto postReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postService.updatePost(id, postReqDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * 글 삭제하기
     */
    @DeleteMapping("/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postService.deletePost(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * 글에 딸린 댓글들 조회하기
     */
    @GetMapping("/{post_id}")
    public ResponseEntity<?> inquireComments(@PathVariable("post_id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postService.inquireComments(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * 페이징 처리된 글들 조회하기
     */
    @GetMapping("infos")
    public ResponseEntity<?> inquirePosts(@RequestParam("page") @Min(0) int page,
                                          @RequestParam("size") @Min(0) int size) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.inquirePosts(page, size));
    }
}
