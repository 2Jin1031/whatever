package zkffl0.whatever.API.dto.member.sign_up;

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
public class SignUpResDto {

    private String name;
    private String phoneNo;
}
