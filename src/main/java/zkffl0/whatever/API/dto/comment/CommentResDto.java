package zkffl0.whatever.API.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Schema(name = "댓 정보")
public class CommentResDto {

    @Schema(example = "나는 1번 ㄱㄱ")
    private String content;

    @Schema(example = "2023-11-17T23:00:00")
    private LocalDateTime useTime;
}
