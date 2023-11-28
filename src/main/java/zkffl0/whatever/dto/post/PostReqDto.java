package zkffl0.whatever.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "페이지 정보")
public class PostReqDto {

    @Schema(example = "다들 들어와서 옷 좀 골라줘")
    private String title;

    @Schema(example = "1번, 2번 중에 뭐가 나아??")
    private String content;
}