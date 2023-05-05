package com.firas.spring.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Firas Chemak
 * @since {{next_version}}
 */
@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("before step "+ stepExecution.getStepName());
        System.out.println("job exec context "+ stepExecution.getJobExecution().getExecutionContext());
        System.out.println("step exec context "+ stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("after step "+ stepExecution.getStepName());
        System.out.println("job exec context "+ stepExecution.getJobExecution().getExecutionContext());
        System.out.println("step exec context "+ stepExecution.getExecutionContext());
        return ExitStatus.COMPLETED;
    }
}
