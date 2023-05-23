package com.firas.spring.batch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Firas Chemak
 * @since {{next_version}}
 */
@RestController
@RequestMapping("api/job")
public class JobController {

    @Autowired JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired Job firstJob;

    @Qualifier("secondJob")
    @Autowired Job secondJob;


    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws
                                                         JobInstanceAlreadyCompleteException,
                                                         JobExecutionAlreadyRunningException,
                                                         JobParametersInvalidException,
                                                         JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder().addJobParameter("currentTime",
                new JobParameter(System.currentTimeMillis(), Long.class)).toJobParameters();

        if (jobName.equals("firstJob")) {
            jobLauncher.run(firstJob, jobParameters);
        } else if (jobName.equals("secondJob")) {
            jobLauncher.run(firstJob, jobParameters);
        }

        return "Job Started...";
    }

}
