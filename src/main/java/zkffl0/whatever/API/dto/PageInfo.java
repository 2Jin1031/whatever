package zkffl0.whatever.API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PageInfo {
    private int page; // 조회하려는 페이지 번호
    private int pageSize; // 한 페이지당 표시할 항목 수
    private Long totalNumber;
    private int totalPages;
}