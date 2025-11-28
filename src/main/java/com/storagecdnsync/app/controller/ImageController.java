package com.storagecdnsync.app.controller;

import com.storagecdnsync.app.dto.FolderImagesResponse;
import com.storagecdnsync.app.service.ImageService;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
    
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{folder}")
    public ResponseEntity<FolderImagesResponse> getImagesByFolder(@PathVariable String folder) {
        try {
            StorageFolder storageFolder = StorageFolder.valueOf(folder.toUpperCase());
            FolderImagesResponse response = imageService.getImagesByFolder(storageFolder);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
