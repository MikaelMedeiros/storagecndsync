package com.storagecdnsync.app.storagecore.impl;

import com.storagecdnsync.app.config.PersonalSiteProperties;
import com.storagecdnsync.app.storagecore.StorageProvider;
import com.storagecdnsync.app.storagecore.domain.FileMetadata;
import com.storagecdnsync.app.storagecore.domain.ImageFile;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class GoogleDriveStorageProvider implements StorageProvider {
    
    private final PersonalSiteProperties properties;

    public GoogleDriveStorageProvider(PersonalSiteProperties properties) {
        this.properties = properties;
    }
    
    @Override
    public List<ImageFile> listFiles(StorageFolder folder) {
        String folderName = getFolderName(folder);
        return List.of();
    }
    
    @Override
    public String generatePublicAccessUrl(FileMetadata metadata) {
        return "https://drive.google.com/uc?id=" + metadata.fileId();
    }
    
    private String getFolderName(StorageFolder folder) {
        String key = folder.name().toLowerCase();
        return properties.getFolders().getOrDefault(key, key);
    }
}
