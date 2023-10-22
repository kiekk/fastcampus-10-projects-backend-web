package com.fastcampus.pass.job.pass;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AddPassesJobConfig {

    @Bean
    Job addPassesJob(final JobRepository jobRepository,
                     final Step addPassesStep) {
        return new JobBuilder("addPassesJob", jobRepository)
                .start(addPassesStep)
                .build();
    }

    @Bean
    Step addPassesStep(final JobRepository jobRepository,
                       final PlatformTransactionManager platformTransactionManager,
                       final Tasklet addPassesTasklet) {
        return new StepBuilder("addPassesStep", jobRepository)
                .tasklet(addPassesTasklet, platformTransactionManager)
                .build();
    }

}
