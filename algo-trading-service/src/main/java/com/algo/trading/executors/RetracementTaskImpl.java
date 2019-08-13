//package com.algo.trading.executors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.algo.trading.core.RetracementService;
//import com.algo.trading.jsons.DataRequest;
//
//@Component
//public class RetracementTaskImpl extends TaskExecutorImpl {
//
//	@Autowired
//	private RetracementTask task;
//
//	@Override
//	public void execute(final DataRequest request) {
//		task.setRequest(request);
//		getExecutorService().submit(task);
//	}
//
//	@Component
//	private class RetracementTask implements RunnableTask {
//
//		private DataRequest request;
//
//		public void setRequest(DataRequest request) {
//			this.request = request;
//		}
//
//		@Autowired
//		private RetracementService service;
//
//		@Override
//		public void run() {
//			service.process(request);
//		}
//	}
//}
