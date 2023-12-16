package zkffl0.whatever.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.Main;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Tag(name = "이미지", description = "이미지과 관련된 모든 것")
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    @RequestMapping(value = "uploadTest", method = RequestMethod.POST)
    public void uploadTestPOST(MultipartFile[] uploadFile) {
        logger.info("uploadTestPOST............");

        // 내가 업로드 파일을 저장할 경로
        String uploadFolder = "C:\\upload";
        for (MultipartFile multipartFile : uploadFile) {
            String uploadFileName = multipartFile.getOriginalFilename();
            // 저장할 파일, 생성자로 경로와 이름을 지정해줌.
            File saveFile = new File(uploadFolder, uploadFileName);

            try {
                // void transferTo(File dest) throws IOException 업로드한 파일 데이터를 지정한 파일에 저장
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
