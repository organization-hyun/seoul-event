package com.hyun.seoul_event.batch.seouleventif.config.myentity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AService {

    private final TestEntityARepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TestEntityA save(TestEntityA entity) throws InterruptedException {
        Thread.sleep(1000);
        if(true) throw new RuntimeException("BSERVICE ERROR");

        return repository.save(entity);
    }
}
