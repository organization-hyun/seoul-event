package com.hyun.seoul_event.batch.seouleventif.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hyun.seoul_event.batch.seouleventif.collector.SeoulEventDataCollector;
import com.hyun.seoul_event.batch.seouleventif.dto.SeoulEventIfDto;
import com.hyun.seoul_event.batch.seouleventif.repository.SeoulEventIfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeoulEventBatchService {

    private final SeoulEventDataCollector dataCollector;
    private final SeoulEventIfRepository repository;

    public void fetchAndSaveAllSeoulEvents() {
        List<JsonNode> collectedData = dataCollector.collectAllEvents();

        for (JsonNode jsonNode : collectedData) {
            try {
                SeoulEventIfDto seoulEventIfDto = SeoulEventIfDto.fromJson(jsonNode);
                repository.save(seoulEventIfDto.toEntity());
            } catch (Exception e) {
                log.error("seoulEventIfDto 변환 중 오류 발생: {} {}", e.getMessage(), jsonNode.toString());
//                throw new RuntimeException(e);
            }
        }

        if (collectedData.isEmpty()) {
            log.info("수집된 데이터가 없습니다.");
        } else {
            // Repository에 저장하는 로직을 추가합니다.
        }
    }
}
