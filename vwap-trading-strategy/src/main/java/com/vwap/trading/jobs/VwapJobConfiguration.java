package com.vwap.trading.jobs;

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

import com.vwap.trading.models.JobDetails;
import com.vwap.trading.models.Vwap;
import com.vwap.trading.steps.StockReader;
import com.vwap.trading.steps.VwapReader;

@Configuration
public class VwapJobConfiguration {

	@Autowired
	private JobBuilderFactory jbf;

	@Autowired
	private StepBuilderFactory sbf;

	@Bean
	@Qualifier("vwap")
	public Job job(
			@Qualifier("topGainerLoser") Step topGainerLoser,
			@Qualifier("calculateVwapStep") Step calculateVwapStep) {
		return jbf.get("vwap-job")
				.start(topGainerLoser)
				.next(calculateVwapStep)
				.build();
	}

	@Bean
	@Qualifier("calculateVwapStep")
	protected Step calculateVwapStep(
			ItemReader<List<VwapReader>> ir, 
			ItemWriter<Vwap> iw,
			ItemProcessor<List<VwapReader>, Vwap> pr) {
		return sbf.get("vwap-step")
				.<List<VwapReader>, Vwap>chunk(100)
				.reader(ir)
				.processor(pr)
				.writer(iw)
				.faultTolerant()
				.skipLimit(10)
				.skip(Exception.class)
				.build();
	}

	@Bean
	@Qualifier("topGainerLoser")
	protected Step topGainerLoser(ItemReader<StockReader> ir,
			ItemWriter<List<JobDetails>> iw,
			ItemProcessor<StockReader, List<JobDetails>> pr) {
		return sbf.get("find-top-losergainer-stocks")
				.<StockReader, List<JobDetails>>chunk(100)
				.reader(ir)
				.processor(pr)
				.writer(iw)
				.faultTolerant()
				.skipLimit(10)
				.skip(Exception.class)
				.build();
	}
}
