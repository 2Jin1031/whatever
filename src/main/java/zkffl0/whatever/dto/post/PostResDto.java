package zkffl0.whatever.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import zkffl0.whatever.repository.post.Post;
import zkffl0.whatever.repository.post.PostRepository;

import java.time.LocalDateTime;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "페이지 정보")
public class PostResDto {

    @Schema(example = "다들 들어와서 옷 좀 골라줘")
    private String title;

    @Schema(example = "1번, 2번 중에 뭐가 나아??")
    private String content;

    @Schema(example = "2023-11-17T23:00:00")
    private LocalDateTime useTime;

    public PostResDto(String title, String content, LocalDateTime useTime) {
        this.title = title;
        this.content = content;
        this.useTime = useTime;
    }

    public PostResDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.useTime = post.getUseTime();
    }
}