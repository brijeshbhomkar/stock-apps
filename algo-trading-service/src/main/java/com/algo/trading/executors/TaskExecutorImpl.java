package com.algo.trading.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;

@Component
public class TaskExecutorImpl implements TaskExecutor {

	private final ExecutorService executorService = Executors.newCachedThreadPool();

	@Override
	public void execute(final DataRequest dataRequest) {
		final ScheduleTask scheduleTask = new ScheduleTask(dataRequest);
		final Future<?> future = executorService.submit(scheduleTask);
		if (future.isDone()) {
			try {
				CandleResponse response = (CandleResponse) future.get();
				System.out.println(response);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
