package com.firas.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Firas Chemak
 * @since {{next_version}}
 */
@Configuration
public class SampleJob {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job firstJob(JobRepository jobRepository, Step firstStep,Step secondStep) {
        return new JobBuilder("first_job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstStep)
                .next(secondStep)
                .build();
    }
    @Bean
    Step firstStep(JobRepository jobRepository ,Tasklet firsTasklet) {
        return new StepBuilder("first_step",jobRepository).tasklet(firsTasklet).transactionManager(transactionManager).build();
    }
    @Bean
    Tasklet firsTasklet() {
        return new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("firstTask");
                return RepeatStatus.FINISHED;
            }
        };
    }


    @Bean
    Step secondStep(JobRepository jobRepository ,Tasklet secondTasklet) {
        return new StepBuilder("second_step",jobRepository).tasklet(secondTasklet).transactionManager(transactionManager).build();
    }




}
