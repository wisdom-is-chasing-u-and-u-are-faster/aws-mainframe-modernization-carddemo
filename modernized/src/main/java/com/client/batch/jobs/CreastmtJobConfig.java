package com.client.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CreastmtJobConfig {
    @Bean
    public Job creastmtJob(JobRepository jobRepository, Step creastmtStep) {
        return new JobBuilder("CREASTMT", jobRepository)
            .start(creastmtStep)
            .build();
    }

    @Bean
    public Step creastmtStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("creastmtStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                // Extract statement details and generate statement print records
                return RepeatStatus.FINISHED;
            }, txManager)
            .build();
    }
}
