package zkffl0.whatever.API.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zkffl0.whatever.API.dto.thumb.ThumbReqDto;
import zkffl0.whatever.API.service.ThumbService;

import javax.validation.Valid;

@Tag(name = "좋아요", description = "좋아요과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/thumb")
public class ThumbController {

    private final ThumbService thumbService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid ThumbReqDto thumbReqDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(thumbService.insert(thumbReqDto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid ThumbReqDto thumbReqDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(thumbService.delete(thumbReqDto));
    }
}