package com.storagecdnsync.app.storagecore.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageFolderTest {

    @Test
    void testAllEnumValues() {
        StorageFolder[] folders = StorageFolder.values();
        
        assertEquals(6, folders.length);
        assertTrue(containsFolder(folders, StorageFolder.BANNER));
        assertTrue(containsFolder(folders, StorageFolder.BIO));
        assertTrue(containsFolder(folders, StorageFolder.PROFILE));
        assertTrue(containsFolder(folders, StorageFolder.GALLERY));
        assertTrue(containsFolder(folders, StorageFolder.DOCUMENTS));
        assertTrue(containsFolder(folders, StorageFolder.TEMP));
    }

    @Test
    void testEnumValueOf() {
        assertEquals(StorageFolder.BANNER, StorageFolder.valueOf("BANNER"));
        assertEquals(StorageFolder.BIO, StorageFolder.valueOf("BIO"));
        assertEquals(StorageFolder.PROFILE, StorageFolder.valueOf("PROFILE"));
        assertEquals(StorageFolder.GALLERY, StorageFolder.valueOf("GALLERY"));
        assertEquals(StorageFolder.DOCUMENTS, StorageFolder.valueOf("DOCUMENTS"));
        assertEquals(StorageFolder.TEMP, StorageFolder.valueOf("TEMP"));
    }

    @Test
    void testEnumValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            StorageFolder.valueOf("INVALID");
        });
    }

    @Test
    void testEnumEquality() {
        StorageFolder folder1 = StorageFolder.BANNER;
        StorageFolder folder2 = StorageFolder.BANNER;
        StorageFolder folder3 = StorageFolder.BIO;

        assertEquals(folder1, folder2);
        assertNotEquals(folder1, folder3);
        assertSame(folder1, folder2);
    }

    private boolean containsFolder(StorageFolder[] folders, StorageFolder target) {
        for (StorageFolder folder : folders) {
            if (folder == target) {
                return true;
            }
        }
        return false;
    }
}
