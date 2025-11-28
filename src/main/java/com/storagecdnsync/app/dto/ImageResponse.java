package com.storagecdnsync.app.dto;

public record ImageResponse(
    String itemImageSrc,
    String thumbnailImageSrc,
    String alt,
    String title
) {
}
