package com.hyun.seoul_event.seoulevent.controller;

import com.hyun.seoul_event.seoulevent.controller.dto.SeoulEventDto;
import com.hyun.seoul_event.seoulevent.service.SeoulEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seoul-events")
public class SeoulEventController {

    private final SeoulEventService seoulEventService;

    @GetMapping
    public Page<SeoulEventDto> getSeoulEventList(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        return seoulEventService.getSeoulEventList(PageRequest.of(page, size));
    }

}
