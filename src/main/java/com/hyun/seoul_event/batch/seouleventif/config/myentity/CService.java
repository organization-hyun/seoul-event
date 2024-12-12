package com.hyun.seoul_event.batch.seouleventif.config.myentity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CService {

    private final TestEntityCRepository repository;

    public TestEntityC save(TestEntityC entity) throws InterruptedException {
        Thread.sleep(1000);
        return repository.save(entity);
    }
}
