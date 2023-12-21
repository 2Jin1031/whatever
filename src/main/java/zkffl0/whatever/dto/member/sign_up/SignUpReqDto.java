package zkffl0.whatever.dto.member.sign_up;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Schema(name = "회원가입")
public class SignUpReqDto {

    @Schema(example = "닉네임")
    private String name;

    @Schema(example ="01012345678")
    private String phoneNo;

    @Schema(example = "mypassword1234")
    private String password;
}
