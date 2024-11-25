package com.hyun.seoul_event.seoulevent.controller;

import com.hyun.seoul_event.seoulevent.controller.dto.SeoulEventsDto;
import com.hyun.seoul_event.seoulevent.service.SeoulEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seoul-events")
public class SeoulEventController {

    private final SeoulEventService seoulEventService;

    @GetMapping
    public SeoulEventsDto getSeoulEvents() {
        return seoulEventService.getSeoulEvents();
    }

}
