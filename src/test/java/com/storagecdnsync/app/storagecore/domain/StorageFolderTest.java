package com.storagecdnsync.app.storagecore.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageFolderTest {    @Test
    void testAllEnumValues() {
        StorageFolder[] folders = StorageFolder.values();
        
        assertEquals(7, folders.length);
        assertTrue(containsFolder(folders, StorageFolder.BANNER));
        assertTrue(containsFolder(folders, StorageFolder.BIO));
        assertTrue(containsFolder(folders, StorageFolder.FOOTER));
        assertTrue(containsFolder(folders, StorageFolder.FORM));
        assertTrue(containsFolder(folders, StorageFolder.MENU));
        assertTrue(containsFolder(folders, StorageFolder.PORTFOLIO));
        assertTrue(containsFolder(folders, StorageFolder.TIPS));
    }

    @Test
    void testEnumValueOf() {
        assertEquals(StorageFolder.BANNER, StorageFolder.valueOf("BANNER"));
        assertEquals(StorageFolder.BIO, StorageFolder.valueOf("BIO"));
        assertEquals(StorageFolder.FOOTER, StorageFolder.valueOf("FOOTER"));
        assertEquals(StorageFolder.FORM, StorageFolder.valueOf("FORM"));
        assertEquals(StorageFolder.MENU, StorageFolder.valueOf("MENU"));
        assertEquals(StorageFolder.PORTFOLIO, StorageFolder.valueOf("PORTFOLIO"));
        assertEquals(StorageFolder.TIPS, StorageFolder.valueOf("TIPS"));
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
