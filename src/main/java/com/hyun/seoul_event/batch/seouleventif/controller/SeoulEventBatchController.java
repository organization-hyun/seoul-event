package com.hyun.seoul_event.batch.seouleventif.controller;

import com.hyun.seoul_event.batch.seouleventif.service.SeoulEventBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("batch/seoul-event")
@RestController
@RequiredArgsConstructor
public class SeoulEventBatchController {

    private final SeoulEventBatchService seoulEventBatchService;

    private final JobLauncher jobLauncher;
    private final Job myJob;

    @RequestMapping("/fetch-and-save-all-events/on-demand")
    public void fetchAndSaveAllEventsOnDemand() {
        seoulEventBatchService.fetchAndSaveAllSeoulEvents();
    }

    @RequestMapping("/on-demand")
    public void onDemand() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // 매번 고유한 파라미터 추가
                    .toJobParameters();
            jobLauncher.run(myJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
