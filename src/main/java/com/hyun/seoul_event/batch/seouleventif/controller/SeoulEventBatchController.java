package com.hyun.seoul_event.batch.seouleventif.controller;

import com.hyun.seoul_event.batch.seouleventif.service.SeoulEventBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("batch/seoul-event")
@RestController
@RequiredArgsConstructor
public class SeoulEventBatchController {


    private final SeoulEventBatchService seoulEventBatchService;

    @RequestMapping("/fetch-and-save-all-events/on-demand")
    public void fetchAndSaveAllEventsOnDemand() {
        seoulEventBatchService.fetchAndSaveAllSeoulEvents();
    }

}
