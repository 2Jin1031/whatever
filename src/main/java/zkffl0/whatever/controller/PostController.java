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

@Tag(name = "글", description = "글과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @RegisterResApi
    @RegisterReqApi
    @PostMapping("/post-info")
    public ResponseEntity<?> registerInfo(@RequestBody PostReqDto postReqDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postService.registerInfo(postReqDto));
        } catch  (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

}
