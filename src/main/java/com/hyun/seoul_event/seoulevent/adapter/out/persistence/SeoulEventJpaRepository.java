package com.hyun.seoul_event.seoulevent.adapter.out.persistence;

import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeoulEventJpaRepository extends JpaRepository<SeoulEvent, Long> {
    List<SeoulEvent> findTop5ByOrderByIdDesc();
}
