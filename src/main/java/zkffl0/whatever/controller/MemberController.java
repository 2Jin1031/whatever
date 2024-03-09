package zkffl0.whatever.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zkffl0.whatever.common.exception.MemberDuplicateException;
import zkffl0.whatever.dto.member.sign_up.SignUpReqDto;
import zkffl0.whatever.dto.member.sign_up.SignUpResDto;
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

    // TODO : 로그인 관련 공부 좀 더 하고 로직 추가
    /** 로그인 */
//    @LoginReqApi
//    @LoginResApi
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginReqDto memberInfo) {
//        try {
//            LoginResDto loginResDto = authService.login(memberInfo);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(loginResDto);
//        }
//        catch(NotFoundException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());
//        }
//        catch(InvalidPasswordException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(e.getMessage());
//        }
//    }
}
