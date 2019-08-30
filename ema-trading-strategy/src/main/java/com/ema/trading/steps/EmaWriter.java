package com.ema.trading.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ema.trading.model.JobDetails;
import com.ema.trading.repository.JobsRepository;

@Component
@Qualifier("emaWriter")
public class EmaWriter implements ItemWriter<List<JobDetails>> {

	@Autowired
	private JobsRepository jobRepository;

	@Override
	public void write(List<? extends List<JobDetails>> items) throws Exception {
		if (!CollectionUtils.isEmpty(items)) {
			items.forEach(i -> {
				jobRepository.save((JobDetails) i);
			});
		}
	}

}
