package com.spring_batch.customer_springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BatchJobController {

    private final JobLauncher jobLauncher;
    private final Job processCustomerJob;


    public BatchJobController(JobLauncher jobLauncher, Job processCustomerJob) {
        this.jobLauncher = jobLauncher;
        this.processCustomerJob = processCustomerJob;
    }
    
    @GetMapping("/run-batch")
    public String runBatch() throws Exception {
        JobParameters params = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();

        jobLauncher.run(processCustomerJob, params);
        return "Batch job Invoked";
    }
    


}
