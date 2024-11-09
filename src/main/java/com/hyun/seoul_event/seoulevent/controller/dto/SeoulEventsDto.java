package com.hyun.seoul_event.seoulevent.controller.dto;

import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SeoulEventsDto {

    private List<SeoulEventDto> seoulEvents;

    public static SeoulEventsDto of(List<SeoulEvent> seoulEvents) {
        return new SeoulEventsDto(
                seoulEvents.stream()
                        .map(SeoulEventDto::of)
                        .collect(Collectors.toList())
        );
    }
}
