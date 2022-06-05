package com.nse.services.india.vix.config;

import com.nse.services.india.vix.model.IndiaVixItem;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private IndiaVixReader indiaVixReader;

    @Autowired
    private IndiaVixWriter indiaVixWriter;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("indiavix-job")
                .incrementer(new RunIdIncrementer())
                .flow(createStep())
                .end()
                .build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("indiavix-step")
                .<IndiaVixItem, IndiaVixItem>chunk(1)
                .reader(reader())
                .writer(indiaVixWriter)
                .build();
    }

    private ItemReader<IndiaVixItem> reader() {
        Resource[] resources = null;
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            resources = patternResolver.getResources("file:/data/*.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultiResourceItemReader<IndiaVixItem> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(indiaVixReader);
        return reader;
    }


}
