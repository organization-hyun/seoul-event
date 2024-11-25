package com.hyun.seoul_event.seoulevent.service;

import com.hyun.seoul_event.seoulevent.controller.dto.SeoulEventListDto;
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

    public SeoulEventListDto getSeoulEvents() {
        List<SeoulEvent> seoulEventList = seoulEventRepository.findTop5ByOrderByIdDesc();
        return SeoulEventListDto.of(seoulEventList);
    }

}
