package zkffl0.whatever.dto.thumb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "좋아요 정보")
public class ThumbReqDto {

    private Long memberId;
    private Long postId;

}
