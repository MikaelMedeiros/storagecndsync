package com.storagecdnsync.app.storagecore.domain;

public record FileMetadata(
    String fileId,
    String fileName,
    StorageFolder folder
) {
}
