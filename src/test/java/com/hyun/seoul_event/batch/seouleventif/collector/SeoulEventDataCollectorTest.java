package com.hyun.seoul_event.batch.seouleventif.collector;

import com.fasterxml.jackson.databind.JsonNode;
import com.hyun.seoul_event.batch.seouleventif.client.SeoulEventApiClient;
import com.hyun.seoul_event.batch.seouleventif.client.SeoulEventApiClientProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Slf4j
class SeoulEventDataCollectorTest {

    @Mock
    private SeoulEventApiClient seoulEventApiClient;

    @Mock
    private SeoulEventApiClientProperties properties;

    @InjectMocks
    private SeoulEventDataCollector dataCollector;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(properties.getApiKey()).thenReturn("test-api-key");
        when(properties.getType()).thenReturn("json");
        when(properties.getService()).thenReturn("ListPublicReservationCulture");
        when(properties.getPageSize()).thenReturn(1000);
    }

    @Test
    void collectAllEvents_ReturnsAccumulatedData_WhenDataIsAvailable() {
        // Mock된 API 호출 응답
        String responsePage1 = "{\"ListPublicReservationCulture\":{\"row\": [{\"SVCNM\": \"Event1\"}]}}";
        String responsePage2 = "{\"ListPublicReservationCulture\":{\"row\": [{\"SVCNM\": \"Event2\"}]}}";
        String noDataResponse = "{\"RESULT\": {\"CODE\":\"INFO-200\"}}";

        when(seoulEventApiClient.fetchEvents(anyString(), anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(responsePage1)
                .thenReturn(responsePage2)
                .thenReturn(noDataResponse);

        // 테스트 실행
        List<JsonNode> result = dataCollector.collectAllEvents();

        // 검증
        log.info("수집된 데이터: {}", result);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Event1", result.get(0).get("SVCNM").asText());
        assertEquals("Event2", result.get(1).get("SVCNM").asText());

        // API가 3번 호출되었는지 검증
        verify(seoulEventApiClient, times(3)).fetchEvents(anyString(), anyString(), anyString(), anyInt(), anyInt());
    }
}
