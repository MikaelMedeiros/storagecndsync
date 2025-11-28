package com.storagecdnsync.app.storagecore;

import com.storagecdnsync.app.storagecore.domain.FileMetadata;
import com.storagecdnsync.app.storagecore.domain.ImageFile;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;

import java.util.List;

public interface StorageProvider {
    
    List<ImageFile> listFiles(StorageFolder folder);
    
    String generatePublicAccessUrl(FileMetadata metadata);
}
