package zkffl0.whatever.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zkffl0.whatever.common.exception.MemberDuplicateException;
import zkffl0.whatever.dto.comment.CommentReqDto;
import zkffl0.whatever.dto.post.PostReqDto;
import zkffl0.whatever.service.CommentService;
import zkffl0.whatever.service.MemberService;
import zkffl0.whatever.utils.swagger.account.RegisterReqApi;
import zkffl0.whatever.utils.swagger.account.RegisterResApi;

@Tag(name = "멤버", description = "멤버과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**   회원가입   */
    @RegisterResApi
    @RegisterReqApi
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody SignUpReqDto signUpReqDto) {
        try{
            SignUpResDto signUpResDto = memberService.createMember(signUpReqDto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(signUpResDto);
        } catch(MemberDuplicateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
