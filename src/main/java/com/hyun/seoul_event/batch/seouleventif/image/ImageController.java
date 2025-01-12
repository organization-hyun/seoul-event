package com.hyun.seoul_event.batch.seouleventif.image;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageDownloader imageDownloader;

    @GetMapping("/download")
    public void downloadImages() {
        imageDownloader.downloadImages();
    }

}
