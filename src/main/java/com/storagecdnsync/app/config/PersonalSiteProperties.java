package com.storagecdnsync.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "personal-site")
public class PersonalSiteProperties {
    
    private String rootFolder;
    private Map<String, String> folders;

    public String getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    public Map<String, String> getFolders() {
        return folders;
    }

    public void setFolders(Map<String, String> folders) {
        this.folders = folders;
    }
}
