package zkffl0.whatever.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "댓 정보")
public class CommentReqDto {

    @Schema(example = "나는 1번 ㄱㄱ")
    private String content;
}
