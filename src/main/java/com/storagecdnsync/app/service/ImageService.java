package com.storagecdnsync.app.service;

import com.storagecdnsync.app.dto.FolderImagesResponse;
import com.storagecdnsync.app.dto.ImageResponse;
import com.storagecdnsync.app.storagecore.StorageProvider;
import com.storagecdnsync.app.storagecore.domain.ImageFile;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    
    private final StorageProvider storageProvider;

    public ImageService(StorageProvider storageProvider) {
        this.storageProvider = storageProvider;
    }

    public FolderImagesResponse getImagesByFolder(StorageFolder folder) {
        List<ImageFile> files = storageProvider.listFiles(folder);
        
        List<ImageResponse> images = files.stream()
            .map(file -> new ImageResponse(
                file.url(),
                file.url(),
                "Image " + file.name(),
                file.name()
            ))
            .collect(Collectors.toList());

        return new FolderImagesResponse(
            folder.name().toLowerCase(),
            images
        );
    }
}
