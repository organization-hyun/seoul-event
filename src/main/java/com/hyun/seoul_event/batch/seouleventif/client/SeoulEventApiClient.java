package com.hyun.seoul_event.batch.seouleventif.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "seoulEventApiClient", url = "${external-api.seoul-event.base-url}")
public interface SeoulEventApiClient {

    @GetMapping("/{apiKey}/{type}/{service}/{startIndex}/{endIndex}")
    String fetchEvents(
            @PathVariable String apiKey,
            @PathVariable String type,
            @PathVariable String service,
            @PathVariable int startIndex,
            @PathVariable int endIndex
    );

}

