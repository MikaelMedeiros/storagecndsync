package com.storagecdnsync.app.controller;

import com.storagecdnsync.app.dto.FolderImagesResponse;
import com.storagecdnsync.app.dto.ImageResponse;
import com.storagecdnsync.app.service.ImageService;
import com.storagecdnsync.app.storagecore.domain.StorageFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetImagesByFolderSuccess() {
        List<ImageResponse> images = List.of(
            new ImageResponse("url1", "url1", "Image 1", "image1.jpg")
        );
        FolderImagesResponse expectedResponse = new FolderImagesResponse("banner", images);
        
        when(imageService.getImagesByFolder(StorageFolder.BANNER)).thenReturn(expectedResponse);

        ResponseEntity<FolderImagesResponse> response = controller.getImagesByFolder("banner");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("banner", response.getBody().folder());
        assertEquals(1, response.getBody().images().size());
        verify(imageService, times(1)).getImagesByFolder(StorageFolder.BANNER);
    }

    @Test
    void testGetImagesByFolderCaseInsensitive() {
        List<ImageResponse> images = List.of();
        FolderImagesResponse expectedResponse = new FolderImagesResponse("bio", images);
        
        when(imageService.getImagesByFolder(StorageFolder.BIO)).thenReturn(expectedResponse);

        ResponseEntity<FolderImagesResponse> response = controller.getImagesByFolder("Bio");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(imageService, times(1)).getImagesByFolder(StorageFolder.BIO);
    }

    @Test
    void testGetImagesByFolderInvalidFolder() {
        ResponseEntity<FolderImagesResponse> response = controller.getImagesByFolder("invalid");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(imageService, never()).getImagesByFolder(any());
    }

    @Test
    void testGetImagesByFolderAllFolders() {
        for (StorageFolder folder : StorageFolder.values()) {
            FolderImagesResponse mockResponse = new FolderImagesResponse(
                folder.name().toLowerCase(), 
                List.of()
            );
            when(imageService.getImagesByFolder(folder)).thenReturn(mockResponse);

            ResponseEntity<FolderImagesResponse> response = controller.getImagesByFolder(
                folder.name().toLowerCase()
            );

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }
}
