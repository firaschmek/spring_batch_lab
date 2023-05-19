package com.firas.spring.batch.config;

import com.firas.spring.batch.listener.FirstJobListener;
import com.firas.spring.batch.processor.FirstItemProcessor;
import com.firas.spring.batch.reader.FirstItemtReader;
import com.firas.spring.batch.writer.FirstItemWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
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
//@EnableBatchProcessing
        //(executionContextSerializerRef = "jacksonSerializer")
public class SampleJob {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job firstJob(JobRepository jobRepository, Step firstStep,Step secondStep, FirstJobListener firstJobListener) {
        return new JobBuilder("first_job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstStep)
                .next(secondStep)
                .listener(firstJobListener)
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


    @Bean
    public Job secondJob(JobRepository jobRepository, Step firstchunkStep,Step firstStep) {
        return new JobBuilder("second_job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstchunkStep)
                .next(firstStep)
                .build();
    }

    @Bean
    Step firstchunkStep(JobRepository jobRepository, FirstItemtReader itemReader,
            ItemProcessor firstItemProcessor , FirstItemWriter firstItemWriter) {
        return new StepBuilder("first_chunk_step",jobRepository)
                .<Integer,Long>chunk(3,transactionManager)
                .reader(itemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .transactionManager(transactionManager).build();
    }

    /*@Bean
    public ExecutionContextSerializer jacksonSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }*/

}
