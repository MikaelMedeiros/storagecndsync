package com.storagecdnsync.app.dto;

import java.util.List;

public record FolderImagesResponse(
    String folder,
    List<ImageResponse> images
) {
}
