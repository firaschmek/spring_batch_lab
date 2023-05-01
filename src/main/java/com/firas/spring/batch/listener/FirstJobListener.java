package com.firas.spring.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Firas Chemak
 * @since {{next_version}}
 */
@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before the job"+ jobExecution.getJobInstance().getJobName());
        System.out.println("job prams"+ jobExecution.getJobParameters());
        System.out.println("job exec context"+ jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("test","dummy data");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after the job"+ jobExecution.getJobInstance().getJobName());
        System.out.println("job prams"+ jobExecution.getJobParameters());
        System.out.println("job exec context"+ jobExecution.getExecutionContext());
    }
}
