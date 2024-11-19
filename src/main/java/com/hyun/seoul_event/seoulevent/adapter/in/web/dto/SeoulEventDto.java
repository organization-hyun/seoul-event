package com.hyun.seoul_event.seoulevent.adapter.in.web.dto;

import com.hyun.seoul_event.seoulevent.domain.SeoulEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeoulEventDto {

    private String title;
    private String region;
    private String location;
    private String imageUrl;

    public static SeoulEventDto of(SeoulEvent seoulEvent) {
        return new SeoulEventDto(
                seoulEvent.getTitle(),
                seoulEvent.getRegion(),
                seoulEvent.getLocation(),
                seoulEvent.getImageUrl()
        );
    }

}
