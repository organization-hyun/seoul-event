package com.hyun.seoul_event.seoulevent.controller.dto;

import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeoulEventDto {

    private Long id;
    private String title;
    private String region;
    private String location;
    private String imageUrl;

    public static SeoulEventDto of(SeoulEvent seoulEvent) {
        return new SeoulEventDto(
                seoulEvent.getId(),
                seoulEvent.getTitle(),
                seoulEvent.getRegion(),
                seoulEvent.getLocation(),
                seoulEvent.getImageUrl()
        );
    }

}
