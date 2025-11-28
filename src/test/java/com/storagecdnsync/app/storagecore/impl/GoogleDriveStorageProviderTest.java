package com.storagecdnsync.app.storagecore.impl;

import com.storagecdnsync.app.storagecore.domain.FileMetadata;
import com.storagecdnsync.app.storagecore.domain.ImageFile;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoogleDriveStorageProviderTest {

    private GoogleDriveStorageProvider provider;

    @BeforeEach
    void setUp() {
        provider = new GoogleDriveStorageProvider();
    }

    @Test
    void testListFilesReturnsEmptyList() {
        List<ImageFile> files = provider.listFiles(StorageFolder.BANNER);
        
        assertNotNull(files);
        assertTrue(files.isEmpty());
    }

    @Test
    void testListFilesWithDifferentFolders() {
        List<ImageFile> bannerFiles = provider.listFiles(StorageFolder.BANNER);
        List<ImageFile> bioFiles = provider.listFiles(StorageFolder.BIO);
        List<ImageFile> profileFiles = provider.listFiles(StorageFolder.PROFILE);
        
        assertNotNull(bannerFiles);
        assertNotNull(bioFiles);
        assertNotNull(profileFiles);
        assertTrue(bannerFiles.isEmpty());
        assertTrue(bioFiles.isEmpty());
        assertTrue(profileFiles.isEmpty());
    }

    @Test
    void testGeneratePublicAccessUrlWithValidMetadata() {
        FileMetadata metadata = new FileMetadata(
            "abc123xyz",
            "image.png",
            StorageFolder.BANNER
        );

        String url = provider.generatePublicAccessUrl(metadata);

        assertNotNull(url);
        assertTrue(url.contains(metadata.fileId()));
        assertEquals("https://drive.google.com/file/d/abc123xyz/view", url);
    }

    @Test
    void testGeneratePublicAccessUrlWithDifferentFileIds() {
        FileMetadata metadata1 = new FileMetadata("file1", "test1.jpg", StorageFolder.BIO);
        FileMetadata metadata2 = new FileMetadata("file2", "test2.png", StorageFolder.PROFILE);

        String url1 = provider.generatePublicAccessUrl(metadata1);
        String url2 = provider.generatePublicAccessUrl(metadata2);

        assertNotNull(url1);
        assertNotNull(url2);
        assertNotEquals(url1, url2);
        assertTrue(url1.contains("file1"));
        assertTrue(url2.contains("file2"));
    }

    @Test
    void testGeneratePublicAccessUrlFormat() {
        FileMetadata metadata = new FileMetadata(
            "test-file-id-123",
            "document.pdf",
            StorageFolder.DOCUMENTS
        );

        String url = provider.generatePublicAccessUrl(metadata);

        assertTrue(url.startsWith("https://drive.google.com/file/d/"));
        assertTrue(url.endsWith("/view"));
    }
}
