package com.hyun.seoul_event.seoulevent.adapter.out.persistence;

import com.hyun.seoul_event.seoulevent.application.port.out.LoadEventPort;
import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeoulEventPersistenceAdapter implements LoadEventPort {

    private final SeoulEventJpaRepository seoulEventJpaRepository;

    @Override
    public List<SeoulEvent> findTop5ByOrderByIdDesc() {
        return seoulEventJpaRepository.findTop5ByOrderByIdDesc();
    }

}
