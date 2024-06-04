package org.cocktail.admin.domain.Ingredient.controller.model;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import org.cocktail.db.ingredient.enums.IngredientCategory;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class IngredientRequest {
    private final String ingredientName;
    private final String enName;
    private final String category;
    private final String description;
    private final MultipartFile image;
    private final String imagePath;

    public IngredientRequest(String ingredientName, String enName, String category, String description, MultipartFile image) {
        this.ingredientName = ingredientName;
        this.enName = enName;
        this.category = category;
        this.description = description;
        this.image = image;
        this.imagePath = createPath(image);
    }

    private String createPath(MultipartFile image){
        return UUID.randomUUID() + "_" + image.getOriginalFilename();
    }

//    public void saveImage(String imageName){
//        try {
//            image.transferTo(new File(imagePath));
//        } catch (IOException e) {
//            // 예외 처리
//        }
//    }

//    private String saveImage(MultipartFile image) {
//        String originalFilename = image.getOriginalFilename();
//        String uuid = UUID.randomUUID().toString();
//        String fileName = uuid + "_" + originalFilename;
//        String savePath = "uploads/" + fileName;
//
//        try {
//            image.transferTo(new File(savePath));
//        } catch (IOException e) {
//            // 예외 처리
//        }
//
//        return savePath;
//    }
}
