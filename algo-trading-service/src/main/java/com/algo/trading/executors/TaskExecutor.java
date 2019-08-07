package com.algo.trading.executors;

import com.algo.trading.pojos.DataRequest;

public interface TaskExecutor {
	public void execute(final DataRequest request);
}
