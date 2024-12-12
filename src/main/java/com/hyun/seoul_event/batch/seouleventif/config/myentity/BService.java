package com.hyun.seoul_event.batch.seouleventif.config.myentity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BService {

    private final TestEntityBRepository repository;

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TestEntityB save(TestEntityB entity) throws InterruptedException {
        Thread.sleep(1000);

        return repository.save(entity);
    }
}
