package com.hyun.seoul_event.seoulevent.adapter.in.web;

import com.hyun.seoul_event.seoulevent.adapter.in.web.dto.SeoulEventListDto;
import com.hyun.seoul_event.seoulevent.application.port.in.ViewEventListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seoul-events")
public class SeoulEventController {

    private final ViewEventListUseCase viewEventListUseCase;

    @GetMapping
    public SeoulEventListDto getSeoulEvents() {
        return viewEventListUseCase.getEventList();
    }

}
