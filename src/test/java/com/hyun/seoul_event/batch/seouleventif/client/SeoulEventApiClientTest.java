package com.hyun.seoul_event.batch.seouleventif.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SeoulEventApiClientTest {

    @Autowired
    private SeoulEventApiClient seoulEventApiClient;

    @Value("${external-api.seoul-event.api-key}")
    private String apiKey;
    private String type = "json";
    private String service = "ListPublicReservationCulture";
    private int pageSize = 5;

    @Test
    void fetchEvents_IntegrationTest() {
        // 실제 API 호출
        String response = seoulEventApiClient.fetchEvents(apiKey, type, service, 1, pageSize);

        log.info("api key: {}", apiKey);
        log.info("API 응답: {}", response);

        // 검증
        assertNotNull(response);
        assertTrue(response.contains("events") || response.contains("RESULT"));
    }

}
