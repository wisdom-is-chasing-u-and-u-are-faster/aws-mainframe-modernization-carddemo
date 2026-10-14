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
public class PosttranJobConfig {
    @Bean
    public Job posttranJob(JobRepository jobRepository, Step posttranStep) {
        return new JobBuilder("POSTTRAN", jobRepository)
            .start(posttranStep)
            .build();
    }

    @Bean
    public Step posttranStep(JobRepository jobRepository, PlatformTransactionManager txManager) {
        return new StepBuilder("posttranStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                // Post daily credit card transactions
                return RepeatStatus.FINISHED;
            }, txManager)
            .build();
    }
}
