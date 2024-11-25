package com.hyun.seoul_event.seoulevent.service;

import com.hyun.seoul_event.seoulevent.controller.dto.SeoulEventsDto;
import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import com.hyun.seoul_event.seoulevent.domain.SeoulEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeoulEventService {

    private final SeoulEventRepository seoulEventRepository;

    public SeoulEventsDto getSeoulEvents() {
        List<SeoulEvent> seoulEvents = seoulEventRepository.findTop5ByOrderByIdDesc();
        return SeoulEventsDto.of(seoulEvents);
    }

}
