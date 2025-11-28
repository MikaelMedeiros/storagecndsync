package com.storagecdnsync.app.storagecore.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ImageFileTest {

    @Test
    void testImageFileCreation() {
        LocalDateTime now = LocalDateTime.now();
        ImageFile imageFile = new ImageFile(
            "test-image.jpg",
            "https://example.com/test-image.jpg",
            1024L,
            now
        );

        assertEquals("test-image.jpg", imageFile.name());
        assertEquals("https://example.com/test-image.jpg", imageFile.url());
        assertEquals(1024L, imageFile.size());
        assertEquals(now, imageFile.lastModified());
    }

    @Test
    void testImageFileEquality() {
        LocalDateTime now = LocalDateTime.now();
        ImageFile file1 = new ImageFile("image.png", "url1", 500L, now);
        ImageFile file2 = new ImageFile("image.png", "url1", 500L, now);

        assertEquals(file1, file2);
        assertEquals(file1.hashCode(), file2.hashCode());
    }

    @Test
    void testImageFileWithNullValues() {
        ImageFile imageFile = new ImageFile(null, null, null, null);

        assertNull(imageFile.name());
        assertNull(imageFile.url());
        assertNull(imageFile.size());
        assertNull(imageFile.lastModified());
    }
}
