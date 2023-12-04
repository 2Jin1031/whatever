package zkffl0.whatever.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "글 정보")
public class PostReqDto {

    @Schema(example = "다들 들어와서 옷 좀 골라줘")
    private String title;

    @Schema(example = "1번, 2번 중에 뭐가 나아??")
    private String content;

    @Schema(example = "2023-11-17T23:00:00")
    private LocalDateTime useTime;
}
