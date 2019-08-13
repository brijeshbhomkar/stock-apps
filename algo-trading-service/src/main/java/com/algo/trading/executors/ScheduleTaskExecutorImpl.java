//package com.algo.trading.executors;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledFuture;
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import com.algo.trading.jsons.Candle;
//import com.algo.trading.jsons.CandleResponse;
//import com.algo.trading.jsons.DataRequest;
//import com.algo.trading.services.DataFetchService;
//
//@Component
//public class ScheduleTaskExecutorImpl implements TaskExecutor {
//
//	final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//	
//	@Override
//	public void execute(final DataRequest request) {
//		final ScheduleTask task = new ScheduleTask(request);
//		ScheduledFuture<?> future = scheduledExecutorService.schedule(task, 2, TimeUnit.MINUTES);
//		if (future.isDone()) {
//			try {
//				CandleResponse response = (CandleResponse) future.get();
//				if (!CollectionUtils.isEmpty(response.getData().getCandles())) {
//					final Candle candle = response.getData().getCandles().get(0);
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private class ScheduleTask implements RunnableTask {
//
//		private DataRequest dataRequest;
//
//		@Autowired
//		private DataFetchService service;
//
//		public ScheduleTask(final DataRequest request) {
//			this.dataRequest = request;
//		}
//
//		@Override
//		public void run() {
//			service.dataExchange(dataRequest);
//		}
//	}
//
//}
