package com.hyun.seoul_event.batch.seouleventif.client;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SeoulEventApiClientProperties {

    @Value("${external-api.seoul-event.api-key}")
    private String apiKey;

    private final String type = "json";

    private final String service = "ListPublicReservationCulture";

    private final int pageSize = 1000;
}
