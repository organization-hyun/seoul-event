package com.hyun.seoul_event.seoulevent.application.port.out;

import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;

import java.util.List;

public interface LoadEventPort {
    List<SeoulEvent> findTop5ByOrderByIdDesc();
}
