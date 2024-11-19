package com.hyun.seoul_event.seoulevent.application.port.in;

import com.hyun.seoul_event.seoulevent.adapter.in.web.dto.SeoulEventListDto;

public interface ViewEventListUseCase {
    SeoulEventListDto getEventList();
}
