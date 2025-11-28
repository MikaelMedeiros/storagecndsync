package com.storagecdnsync.app.storagecore.domain;

import java.time.LocalDateTime;

public record ImageFile(
    String name,
    String url,
    Long size,
    LocalDateTime lastModified
) {
}
