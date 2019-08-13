package com.algo.trading.services;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.algo.trading.entities.StockJob;
import com.algo.trading.repositories.StockJobRepository;

@Service
public class SchedulingService {

	@Autowired
	private DataFetchService dataService;

	@Autowired
	private StockJobRepository jobRepository;

	private BlockingQueue<StockJob> arrayBlockingQueue = new ArrayBlockingQueue<>(20);

	@Scheduled(cron = "0/5 15 9 ? * MON,TUE,WED,THU,FRI *")
	public void start() {
		if (!arrayBlockingQueue.isEmpty()) {
			final StockJob stockJob = arrayBlockingQueue.poll();
			System.out.println(" Cron job started for " + stockJob.toString());

		}
	}

	@Scheduled(cron = "0/10 15 9 ? * MON,TUE,WED,THU,FRI *")
	public void activeJobs() {
		System.out.println(" Running cron job to get the jobs every 10 minutes ");
		final List<StockJob> jobs = jobRepository.findAll().stream().filter(j -> j.getStatus().equals("Active"))
				.collect(Collectors.toList());
		jobs.forEach(j -> {
			arrayBlockingQueue.add(j);
		});
	}
}
