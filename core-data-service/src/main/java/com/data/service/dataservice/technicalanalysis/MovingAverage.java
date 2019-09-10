package com.data.service.dataservice.technicalanalysis;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

	private Queue<Long> queue;
	private int maxSize;
	private Double sum;

	public MovingAverage(int size) {
		queue = new LinkedList<Long>();
		maxSize = size;
		sum = 0.0;
	}

	public double next(long val) {
		if (queue.size() == maxSize) {
			sum -= queue.remove();
		}

		queue.add(val);
		sum += val;
		return sum / queue.size();
	}
	
}
