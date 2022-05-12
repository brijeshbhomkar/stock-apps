package com.vwap.trading.steps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vwap.trading.jsons.Candles;
import com.vwap.trading.models.JobDetails;
import com.vwap.trading.models.Request;
import com.vwap.trading.repositories.JobsRepository;
import com.vwap.trading.services.DataPoolingService;

@Component
public class VwapItemReader implements ItemReader<List<VwapReader>> {

	@Autowired
	private DataPoolingService dataPoolingService;

	@Autowired
	private JobsRepository jobsRepository;

	private BlockingQueue<JobDetails> jobQueue;

	final List<VwapReader> readers = new ArrayList<>();

	@Override
	public List<VwapReader> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		final List<JobDetails> jobs = jobsRepository.findAll();
		if (!CollectionUtils.isEmpty(jobs)) {
			jobQueue = new ArrayBlockingQueue<>(jobs.size() + 1);
			jobs.forEach(j -> {
				jobQueue.add(j);
				jobsRepository.delete(j);
			});
		}
		while (!jobQueue.isEmpty()) {
			final JobDetails job = jobQueue.remove();
			final VwapReader vwapReader = new VwapReader();
			final Request request = new Request();
			request.setSymbol(Long.toString(job.getSymbolId()));
			request.setSymbolName(job.getSymbol());
			request.setUserId(job.getUserId());
			request.setTimeframe(job.getTimeframe());
			request.setFromDate(LocalDate.now().toString());
			request.setToDate(LocalDate.now().toString());
			final Candles response = dataPoolingService.dataExchange(request);
			if (response != null) {
				vwapReader.setSymbolId(Long.toString(job.getSymbolId()));
				vwapReader.setSymbolName(job.getSymbol());
				vwapReader.setCandles(response);
				readers.add(vwapReader);
			}
		}
		return readers;
	}

}
