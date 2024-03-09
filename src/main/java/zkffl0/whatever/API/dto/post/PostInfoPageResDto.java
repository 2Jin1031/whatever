package zkffl0.whatever.API.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zkffl0.whatever.API.dto.PageInfo;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostInfoPageResDto {
    private List<?> postInfos;
    private PageInfo pageInfo;
}
