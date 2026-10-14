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
public class IntcalcJobConfig {
    @Bean
    public Job intcalcJob(JobRepository jobRepository, Step intcalcStep) {
        return new JobBuilder("INTCALC", jobRepository)
            .start(intcalcStep)
            .build();
    }

    @Bean
    public Step intcalcStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("intcalcStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                // Calculate monthly interest and apply to accounts
                return RepeatStatus.FINISHED;
            }, txManager)
            .build();
    }
}
