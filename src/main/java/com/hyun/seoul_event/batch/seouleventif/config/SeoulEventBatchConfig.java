package com.hyun.seoul_event.batch.seouleventif.config;

import com.hyun.seoul_event.batch.seouleventif.config.myentity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SeoulEventBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final AService aService;
    private final BService bService;
    private final CService cService;

    @Bean
    public Job myJob() {
        return new JobBuilder("myJob", jobRepository)
                .start(myStep())
                .build();
    }

    @Bean
    public Step myStep() {
        return new StepBuilder("myStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    executeWithRollback(() -> aService.save(new TestEntityA()), "AService Error, Rollback");
                    executeWithLogging(() -> bService.save(new TestEntityB()), "BService Error, Logging");
                    executeWithLogging(() -> cService.save(new TestEntityC()), "CService Error, Logging");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    private void executeWithRollback(TaskExecutor task, String errorMessage) {
        try {
            task.execute();
        } catch (Exception e) {
            throw new RuntimeException(errorMessage, e); // 전체 롤백
        }
    }

    private void executeWithLogging(TaskExecutor task, String errorMessage) {
        try {
            task.execute();
        } catch (Exception e) {
            System.err.println(errorMessage + ": " + e.getMessage()); // 로그 기록
        }
    }

    @FunctionalInterface
    public interface TaskExecutor {
        void execute() throws Exception;
    }
}
