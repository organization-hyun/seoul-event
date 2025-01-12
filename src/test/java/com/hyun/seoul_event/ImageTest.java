package com.hyun.seoul_event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class ImageTest {

    @Autowired ImageApiTest imageApiTest;

    @Value
    private String imageUrl = "${api.image.url}";
    @Test
    public void test() throws IOException {

        byte[] imageByte = imageApiTest.fetchImage();
        Files.write(Paths.get(imageUrl +  "test.jpg"), imageByte);

    }

    @FeignClient(name = "imageApiTest", url = "https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=17261001711188BN8YGBEHEGLSF0LPVC6AKPDE")
    public interface ImageApiTest {

        @GetMapping
        byte[] fetchImage();

    }
}
