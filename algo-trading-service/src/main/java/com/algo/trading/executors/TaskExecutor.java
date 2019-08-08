package com.algo.trading.executors;

import com.algo.trading.jsons.DataRequest;

public interface TaskExecutor {
	public void execute(final DataRequest request);
}
