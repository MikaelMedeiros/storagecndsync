package com.storagecdnsync.app.storagecore.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileMetadataTest {

    @Test
    void testFileMetadataCreation() {
        FileMetadata metadata = new FileMetadata(
            "file-id-123",
            "document.pdf",
            StorageFolder.PORTFOLIO
        );

        assertEquals("file-id-123", metadata.fileId());
        assertEquals("document.pdf", metadata.fileName());
        assertEquals(StorageFolder.PORTFOLIO, metadata.folder());
    }

    @Test
    void testFileMetadataEquality() {
        FileMetadata metadata1 = new FileMetadata("id1", "file.txt", StorageFolder.FOOTER);
        FileMetadata metadata2 = new FileMetadata("id1", "file.txt", StorageFolder.FOOTER);

        assertEquals(metadata1, metadata2);
        assertEquals(metadata1.hashCode(), metadata2.hashCode());
    }

    @Test
    void testFileMetadataWithDifferentFolders() {
        FileMetadata bannerMetadata = new FileMetadata("id1", "banner.jpg", StorageFolder.BANNER);
        FileMetadata bioMetadata = new FileMetadata("id2", "bio.txt", StorageFolder.BIO);
        FileMetadata portfolioMetadata = new FileMetadata("id3", "portfolio.png", StorageFolder.PORTFOLIO);

        assertNotEquals(bannerMetadata, bioMetadata);
        assertNotEquals(bioMetadata, portfolioMetadata);
        assertEquals(StorageFolder.BANNER, bannerMetadata.folder());
        assertEquals(StorageFolder.BIO, bioMetadata.folder());
        assertEquals(StorageFolder.PORTFOLIO, portfolioMetadata.folder());
    }
}
