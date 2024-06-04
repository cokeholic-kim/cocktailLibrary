package org.cocktail.admin.common;

import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {
//    @Value("${file.add}")
//    public static String UPLOAD_PATH = System.getProperty("user.dir") +"\\admin\\src\\main\\resources\\static\\imgs";
//    public static String UPLOAD_PATH = System.getProperty("user.dir") +"\\admin\\src\\main\\webapp\\imgs\\";

    //외부폴더
    public static String UPLOAD_PATH = "file:///C:/Users/kimad/Desktop/JavaPortpolio/coctail_library_imgs/";
    private String uploadPath = "C:/Users/kimad/Desktop/JavaPortpolio/coctail_library_imgs/";
    public void saveFile(MultipartFile file, String fileName) {
        try {
            file.transferTo(new File(uploadPath, fileName));
        } catch (IOException e) {
            log.error("Error occurred while saving file: {}", e.getMessage(), e);
        }
    }
}
