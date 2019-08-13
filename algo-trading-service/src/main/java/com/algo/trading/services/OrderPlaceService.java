package com.algo.trading.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.algo.trading.entities.StockOrder;
import com.algo.trading.repositories.OrderRepository;

@Service
public class OrderPlaceService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	private BlockingQueue<StockOrder> stockOrderQueue = new ArrayBlockingQueue<>(10);
	
	public void saveOrder(final StockOrder stockOrder) {
		orderRepository.save(stockOrder);
	}
	
	@Scheduled(cron="0 0/5 * * * 1-5")
	public void placeOrder() {
		System.out.println("Placing order");
	}
	
	@Scheduled(cron="0 0/5 * * * 1-5")
	public void findOrder() {
		System.out.println("finding order");
	}
}
