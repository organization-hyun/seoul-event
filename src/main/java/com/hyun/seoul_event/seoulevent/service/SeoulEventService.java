package com.hyun.seoul_event.seoulevent.service;

import com.hyun.seoul_event.seoulevent.controller.dto.SeoulEventDto;
import com.hyun.seoul_event.seoulevent.domain.SeoulEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeoulEventService {

    private final SeoulEventRepository seoulEventRepository;

    public Page<SeoulEventDto> getSeoulEventList(PageRequest pageRequest) {
        return seoulEventRepository.findAll(pageRequest)
                .map(SeoulEventDto::of);
    }

}
