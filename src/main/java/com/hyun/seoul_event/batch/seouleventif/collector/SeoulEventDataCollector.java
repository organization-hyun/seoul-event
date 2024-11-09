package com.hyun.seoul_event.batch.seouleventif.collector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyun.seoul_event.batch.seouleventif.client.SeoulEventApiClient;
import com.hyun.seoul_event.batch.seouleventif.client.SeoulEventApiClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeoulEventDataCollector {

    private final SeoulEventApiClient seoulEventApiClient;
    private final SeoulEventApiClientProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<JsonNode> collectAllEvents() {
        List<JsonNode> allRows = new ArrayList<>();
        int startIndex = 1;

        while (true) {
            String response = fetchEvents(startIndex, startIndex + properties.getPageSize() - 1);
            if (response == null || !isValidJson(response) || isNoDataResponse(response)) {
                break;
            }

            List<JsonNode> rows = extractRowsFromResponse(response);
            if (rows != null) {
                allRows.addAll(rows);
            }

            startIndex += properties.getPageSize();
        }

        return allRows;
    }

    private String fetchEvents(int startIndex, int endIndex) {
        try {
            return seoulEventApiClient.fetchEvents(
                    properties.getApiKey(),
                    properties.getType(),
                    properties.getService(),
                    startIndex,
                    endIndex
            );
        } catch (Exception e) {
            log.error("API 호출 실패: {}", e.getMessage());
            return null;
        }
    }

    private List<JsonNode> extractRowsFromResponse(String response) {
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode rowsNode = rootNode.path("ListPublicReservationCulture").path("row");

            // ArrayNode를 사용하여 데이터를 가져옴
            if (rowsNode.isArray()) {
                List<JsonNode> rowsList = new ArrayList<>();
                rowsNode.forEach(rowsList::add);
                return rowsList;
            }
        } catch (Exception e) {
            log.error("JSON 파싱 오류: {}", e.getMessage());
        }
        return null;
    }

    private boolean isNoDataResponse(String response) {
        return response.contains("\"CODE\":\"INFO-200\"");
    }

    /**
     * 응답이 유효한 JSON 형식인지 확인
     */
    private boolean isValidJson(String response) {
        try {
            objectMapper.readTree(response);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 JSON 응답: {}", response);
            return false;
        }
    }
}
