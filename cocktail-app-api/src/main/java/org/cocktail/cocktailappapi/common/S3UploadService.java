package org.cocktail.cocktailappapi.common;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;
    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public FileEntity saveFile(MultipartFile multipartFile, String userName) throws IOException {
        UserEntity userEntity = userRepository.findByEmail(userName).orElseThrow(IllegalArgumentException::new);

        String fileName = createFileName(multipartFile);

        ObjectMetadata metadata = getObjectMetadata(multipartFile);

        amazonS3.putObject(bucket, fileName, multipartFile.getInputStream(), metadata);

        return FileEntity.builder()
                .fileName(fileName)
                .filePath(amazonS3.getUrl(bucket, fileName).toString())
                .fileSize(multipartFile.getSize())
                .fileType(multipartFile.getContentType())
                .createdAt(LocalDateTime.now())
                .uploader(userEntity)
                .build();
    }
    public static String createFileName(MultipartFile file){
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }

    public void deleteFile(String fileName) {
        amazonS3.deleteObject(bucket, fileName);
    }

    public FileEntity updateFile(String oldFileName, MultipartFile newFile, String uploader) throws IOException {
        //기존이미지를 삭제
        deleteFile(oldFileName);
        //새로운 이미지를 추가
        return saveFile(newFile, uploader);
    }

    private static ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        return objectMetadata;
    }
}
