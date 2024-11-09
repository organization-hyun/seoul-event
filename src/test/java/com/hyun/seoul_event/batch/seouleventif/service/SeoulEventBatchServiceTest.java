package com.hyun.seoul_event.batch.seouleventif.service;

import com.hyun.seoul_event.batch.seouleventif.SeoulEventIf;
import com.hyun.seoul_event.batch.seouleventif.repository.SeoulEventIfRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false) // 실제 DB에 저장하고 싶을 경우 롤백하지 않음
public class SeoulEventBatchServiceTest {

    @Autowired
    private SeoulEventBatchService batchService;

    @Autowired
    private SeoulEventIfRepository repository;

    @Test
    void fetchAndSaveAllSeoulEvents_IntegrationTest() {
        // 서비스 메서드 호출
        batchService.fetchAndSaveAllSeoulEvents();

        // 데이터베이스에서 저장된 이벤트 조회
        List<SeoulEventIf> savedEvents = repository.findAll();

        // 검증
        assertFalse(savedEvents.isEmpty(), "DB에 저장된 데이터가 없습니다.");
        savedEvents.forEach(event -> log.info("저장된 이벤트: {}", event.getServiceName()));
    }
}
