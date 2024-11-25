package com.hyun.seoul_event.seoulevent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeoulEventRepository extends JpaRepository<SeoulEvent, Long> {
    List<SeoulEvent> findTop5ByOrderByIdDesc();
}
