package com.storagecdnsync.app.service;

import com.storagecdnsync.app.dto.FolderImagesResponse;
import com.storagecdnsync.app.dto.ImageResponse;
import com.storagecdnsync.app.storagecore.StorageProvider;
import com.storagecdnsync.app.storagecore.domain.ImageFile;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageServiceTest {

    @Mock
    private StorageProvider storageProvider;

    @InjectMocks
    private ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetImagesByFolderWithMultipleImages() {
        List<ImageFile> files = List.of(
            new ImageFile("image1.jpg", "https://drive.google.com/uc?id=abc123", 1024L, LocalDateTime.now()),
            new ImageFile("image2.png", "https://drive.google.com/uc?id=xyz789", 2048L, LocalDateTime.now())
        );
        
        when(storageProvider.listFiles(StorageFolder.BANNER)).thenReturn(files);

        FolderImagesResponse response = imageService.getImagesByFolder(StorageFolder.BANNER);

        assertEquals("banner", response.folder());
        assertEquals(2, response.images().size());
        
        ImageResponse img1 = response.images().get(0);
        assertEquals("https://drive.google.com/uc?id=abc123", img1.itemImageSrc());
        assertEquals("https://drive.google.com/uc?id=abc123", img1.thumbnailImageSrc());
        assertEquals("Image image1.jpg", img1.alt());
        assertEquals("image1.jpg", img1.title());
        
        verify(storageProvider, times(1)).listFiles(StorageFolder.BANNER);
    }

    @Test
    void testGetImagesByFolderWithEmptyList() {
        when(storageProvider.listFiles(StorageFolder.BIO)).thenReturn(List.of());

        FolderImagesResponse response = imageService.getImagesByFolder(StorageFolder.BIO);

        assertEquals("bio", response.folder());
        assertTrue(response.images().isEmpty());
        verify(storageProvider, times(1)).listFiles(StorageFolder.BIO);
    }

    @Test
    void testGetImagesByFolderForAllFolderTypes() {
        for (StorageFolder folder : StorageFolder.values()) {
            when(storageProvider.listFiles(folder)).thenReturn(List.of());

            FolderImagesResponse response = imageService.getImagesByFolder(folder);

            assertEquals(folder.name().toLowerCase(), response.folder());
            verify(storageProvider, times(1)).listFiles(folder);
        }
    }

    @Test
    void testImageResponseMapping() {
        ImageFile file = new ImageFile(
            "test.jpg", 
            "https://example.com/test.jpg", 
            500L, 
            LocalDateTime.now()
        );
        
        when(storageProvider.listFiles(StorageFolder.PORTFOLIO)).thenReturn(List.of(file));

        FolderImagesResponse response = imageService.getImagesByFolder(StorageFolder.PORTFOLIO);

        assertEquals(1, response.images().size());
        ImageResponse image = response.images().get(0);
        assertEquals(file.url(), image.itemImageSrc());
        assertEquals(file.url(), image.thumbnailImageSrc());
        assertEquals("Image " + file.name(), image.alt());
        assertEquals(file.name(), image.title());
    }
}
