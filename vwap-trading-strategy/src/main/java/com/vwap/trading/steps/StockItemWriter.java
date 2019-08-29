package com.vwap.trading.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vwap.trading.models.JobDetails;
import com.vwap.trading.repositories.JobsRepository;

@Component
public class StockItemWriter implements ItemWriter<List<JobDetails>> {

	@Autowired
	private JobsRepository jobRepository;

	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends List<JobDetails>> items) throws Exception {
		final List<JobDetails> jobDetails = (List<JobDetails>) items;
		if (!CollectionUtils.isEmpty(jobDetails)) {
			items.forEach(s -> {
				s.forEach(k -> {
					if (!jobRepository.existsById(k.getSymbolId())) {
						jobRepository.save(k);
					}
				});
			});
		}
	}

}
