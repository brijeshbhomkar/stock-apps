package com.ema.trading.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ema.trading.model.JobDetails;
import com.ema.trading.model.Symbol;


@Configuration
public class EmaJobConfiguration {

	@Autowired
	private JobBuilderFactory jbf;

	@Autowired
	private StepBuilderFactory sbf;

	@Bean
	@Qualifier("ema")
	public Job job(
			@Qualifier("marketscan") Step marketscan) {
		return jbf.get("ema-job")
				.start(marketscan)
				.build();
	}

	@Bean
	@Qualifier("marketscan")
	protected Step marketscan(
			ItemReader<List<Symbol>> ir,
			ItemWriter<List<JobDetails>> iw,
			ItemProcessor<List<Symbol>, List<JobDetails>> pr) {
		return sbf.get("generate-jobs")
				.<List<Symbol>, List<JobDetails>>chunk(100)
				.reader(ir)
				.processor(pr)
				.writer(iw)
				.faultTolerant()
				.skipLimit(10)
				.skip(Exception.class)
				.build();
	}
}
