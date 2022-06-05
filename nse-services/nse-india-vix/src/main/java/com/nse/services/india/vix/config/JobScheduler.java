package com.nse.services.india.vix.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Log4j2
@Configuration
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    public void startLoadingData() throws Exception {
        try {
            log.info("Batch job starting");
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
            jobLauncher.run(job, jobParameters);
            log.info("Batch job executed successfully\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
