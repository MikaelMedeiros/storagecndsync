package com.storagecdnsync.app.config;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PersonalSitePropertiesTest {

    @Test
    void testSetAndGetRootFolder() {
        PersonalSiteProperties properties = new PersonalSiteProperties();
        properties.setRootFolder("personal-site");

        assertEquals("personal-site", properties.getRootFolder());
    }

    @Test
    void testSetAndGetFolders() {
        PersonalSiteProperties properties = new PersonalSiteProperties();
        Map<String, String> folders = Map.of(
            "banner", "banner",
            "bio", "bio",
            "portfolio", "portfolio"
        );
        properties.setFolders(folders);

        assertEquals(3, properties.getFolders().size());
        assertEquals("banner", properties.getFolders().get("banner"));
        assertEquals("bio", properties.getFolders().get("bio"));
        assertEquals("portfolio", properties.getFolders().get("portfolio"));
    }

    @Test
    void testNullValues() {
        PersonalSiteProperties properties = new PersonalSiteProperties();
        
        assertNull(properties.getRootFolder());
        assertNull(properties.getFolders());
    }
}
