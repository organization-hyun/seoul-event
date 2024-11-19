package com.hyun.seoul_event.seoulevent.adapter.in.web.dto;

import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SeoulEventListDto {

    private List<SeoulEventDto> seoulEvents;

    public static SeoulEventListDto of(List<SeoulEvent> seoulEventList) {
        return new SeoulEventListDto(
                seoulEventList.stream()
                        .map(SeoulEventDto::of)
                        .collect(Collectors.toList())
        );
    }
}
