package com.algo.trading.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.algo.trading.pojos.CandleResponse;
import com.algo.trading.pojos.DataRequest;

@Component
public class ScheduleTask implements RunnableTask {

	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	
	private DataRequest dataRequest;

	public ScheduleTask(final DataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

	@Override
	public void run() {
		
		final DataFetchTask task = new DataFetchTask();
		task.setRequest(dataRequest);
		
		ScheduledFuture<?> future = scheduledExecutorService.schedule(task, 2, TimeUnit.MINUTES);
		if (future.isDone()) {
			try {
				CandleResponse response = (CandleResponse) future.get();
				System.out.println(" Printing schedule future " + response);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
