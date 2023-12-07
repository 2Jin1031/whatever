package zkffl0.whatever.dto.thumb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Schema(name = "좋아요 정보")
public class ThumbResDto {

    private Long memberId;
    private Long postId;
}
