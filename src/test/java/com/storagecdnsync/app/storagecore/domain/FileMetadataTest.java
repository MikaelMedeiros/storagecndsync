package com.storagecdnsync.app.storagecore.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileMetadataTest {

    @Test
    void testFileMetadataCreation() {
        FileMetadata metadata = new FileMetadata(
            "file-id-123",
            "document.pdf",
            StorageFolder.DOCUMENTS
        );

        assertEquals("file-id-123", metadata.fileId());
        assertEquals("document.pdf", metadata.fileName());
        assertEquals(StorageFolder.DOCUMENTS, metadata.folder());
    }

    @Test
    void testFileMetadataEquality() {
        FileMetadata metadata1 = new FileMetadata("id1", "file.txt", StorageFolder.TEMP);
        FileMetadata metadata2 = new FileMetadata("id1", "file.txt", StorageFolder.TEMP);

        assertEquals(metadata1, metadata2);
        assertEquals(metadata1.hashCode(), metadata2.hashCode());
    }

    @Test
    void testFileMetadataWithDifferentFolders() {
        FileMetadata bannerMetadata = new FileMetadata("id1", "banner.jpg", StorageFolder.BANNER);
        FileMetadata bioMetadata = new FileMetadata("id2", "bio.txt", StorageFolder.BIO);
        FileMetadata profileMetadata = new FileMetadata("id3", "profile.png", StorageFolder.PROFILE);

        assertNotEquals(bannerMetadata, bioMetadata);
        assertNotEquals(bioMetadata, profileMetadata);
        assertEquals(StorageFolder.BANNER, bannerMetadata.folder());
        assertEquals(StorageFolder.BIO, bioMetadata.folder());
        assertEquals(StorageFolder.PROFILE, profileMetadata.folder());
    }
}
