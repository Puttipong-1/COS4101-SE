package ru.se.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {
    private static final Logger LOGGER= LoggerFactory.getLogger(ImageController.class);
    @GetMapping("/image/{code}")
    public ResponseEntity<byte[]> getImage(@PathVariable("code") String productCode){
        try{
            Path path= Paths.get("ProductImage/"+productCode);
            byte[] image= Files.readAllBytes(path);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        }catch(Exception ex){
            LOGGER.info(ex.toString());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
