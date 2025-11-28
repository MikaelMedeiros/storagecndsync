package com.storagecdnsync.app.storagecore.impl;

import com.storagecdnsync.app.storagecore.StorageProvider;
import com.storagecdnsync.app.storagecore.domain.FileMetadata;
import com.storagecdnsync.app.storagecore.domain.ImageFile;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class GoogleDriveStorageProvider implements StorageProvider {
    
    @Override
    public List<ImageFile> listFiles(StorageFolder folder) {
        return List.of();
    }
    
    @Override
    public String generatePublicAccessUrl(FileMetadata metadata) {
        return "https://drive.google.com/file/d/" + metadata.fileId() + "/view";
    }
}
