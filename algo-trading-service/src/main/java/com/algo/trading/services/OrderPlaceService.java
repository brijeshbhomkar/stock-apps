package com.algo.trading.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algo.trading.executors.TaskExecutorImpl;

@Service
public class OrderPlaceService {
	
	@Autowired
	private TaskExecutorImpl taskScheduler;

}
