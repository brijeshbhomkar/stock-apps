package com.data.service.dataservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AysnchronousService {

	@Autowired
	private TaskExecutor taskExecutor;

	public void async(final Runnable task) {
		taskExecutor.execute(task);
	}

}
