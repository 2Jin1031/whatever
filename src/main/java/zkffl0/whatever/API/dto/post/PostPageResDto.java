package zkffl0.whatever.API.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import zkffl0.whatever.API.dto.PageInfo;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostPageResDto {
    private PageInfo pageInfo;
    private List<PostResDto> postList;
}
