package com.hyun.seoul_event.batch.seouleventif.image;

import com.hyun.seoul_event.batch.seouleventif.SeoulEventIf;
import com.hyun.seoul_event.batch.seouleventif.repository.SeoulEventIfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageDownloader {

    private final SeoulEventIfRepository seoulEventIfRepository;

    @Value("${app.image-save-path}")
    private String imageSavePath;

    private final RestTemplate restTemplate = new RestTemplate();

    public void downloadImages() {
        List<SeoulEventIf> entities = seoulEventIfRepository.findAll();


        for (int i = 0; i < 10; i++) {
            String fullUrl = entities.get(i).getImagePath();

            try {
                // 이미지 요청
                ResponseEntity<byte[]> response = restTemplate.getForEntity(fullUrl, byte[].class);
                byte[] imageBytes = response.getBody();

                // 파일명 결정
                String fileName = extractFileName(response.getHeaders(), fullUrl);

                // 이미지 저장
                saveImageToFile(fileName, imageBytes);
            } catch (Exception e) {
                System.err.println("Failed to download image from URL: " + fullUrl);
                e.printStackTrace();
            } finally {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private String extractFileName(HttpHeaders headers, String fullUrl) {
        // Content-Disposition 헤더에서 파일명 추출
        String contentDisposition = headers.getFirst(HttpHeaders.CONTENT_DISPOSITION);
        if (contentDisposition != null && contentDisposition.contains("filename=")) {
            String fileName = contentDisposition.split("filename=")[1];
            return fileName.replace("\"", "").trim(); // 따옴표 제거
        }

        // Content-Disposition 헤더가 없으면 현재 일시 사용
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        // 기본 파일명 fallback
        return timestamp + "_" + fullUrl.substring(fullUrl.lastIndexOf('/') + 1);
    }

    private void saveImageToFile(String fileName, byte[] imageBytes) throws IOException {
        // 저장 경로 설정
        File outputFile = new File(imageSavePath, fileName);

        // 디렉토리 생성
        File outputDir = outputFile.getParentFile();
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // 파일 저장
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(imageBytes);
            System.out.println("Image saved: " + outputFile.getAbsolutePath());
        }
    }
}

