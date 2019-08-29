package com.vwap.trading.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vwap.trading.repositories.JobsRepository;

@Service
public class VwapService {

	@Autowired
	private JobsRepository jobRepository;

	@Autowired
	@Qualifier("vwap")
	private Job vwapJob;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobOperator jobOperator;

	@Scheduled(cron = "0 30 9 * * 1-5")
	public void loserGainerStocks() {
		// get top gainers and losers
	}

	@Scheduled(cron = "0 30/2 9 * * 1-5")
	public void startCalculatingVwap() {
		// start vwap every 2 minutes and calculate vwap
	}

	@Scheduled(cron = "0 45/2 9 * * 1-5")
	public void verifyVwap() {
		// verify vwap above or below and generate orders
	}

	@Scheduled(cron = "0 45/1 9 * * 1-5")
	public void orderPlace() {
		// pick up orders and place orders and execute and save profit loss
	}
//
//	@Scheduled(cron = "0 35 15 * * 1-5")
//	public void stop() {
//		// stop all jobs
//		try {
//			jobOperator.getRunningExecutions("vwap-job");
//		} catch (NoSuchJobException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
