package com.algo.trading.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.algo.trading.entities.OrderJob;
import com.algo.trading.entities.StockOrder;
import com.algo.trading.entities.StockRequest;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.DataRequest;
import com.algo.trading.repositories.OrderJobRepository;
import com.algo.trading.repositories.OrderRepository;
import com.algo.trading.repositories.OrderResultRepository;
import com.algo.trading.utils.TradeStatus;
import com.algo.trading.utils.TradeType;

@Service
public class OrderPlaceService {

	private static final Logger logger = LoggerFactory.getLogger(OrderPlaceService.class);

	@Autowired
	private OrderJobRepository orderJobRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderResultRepository orderAuditRepository;

	@Autowired
	private ZerodhaService zerodhaService;
	
	@Autowired
	private DataPoolingService dataPoolingService;

	private BlockingQueue<OrderJob> stockOrderQueue = new ArrayBlockingQueue<>(10);

	private BlockingQueue<StockOrder> activeOrderQueue = new ArrayBlockingQueue<>(5);

	final List<StockOrder> orders = new ArrayList<>();
	
	@PostConstruct
	public void clean() {
		orderJobRepository.deleteAll();
		orderRepository.deleteAll();
	}

	public void saveOrder(final OrderJob stockOrder) {
		logger.debug(" Saving stock order ", stockOrder.getSymbolId());
		try {
			orderJobRepository.save(stockOrder);
		} catch (Exception e) {
			logger.error("Failed to save stock order", e);
		}
	}
	
	@Scheduled(cron="0 0/2 * * * 1-5")
	public void profitLoss() {
		final BlockingQueue<Candle> candle = dataPoolingService.getResponseQueue();
		if (candle != null && !candle.isEmpty()) {
			System.out.println(candle.poll().toString());
		}
	}

	@Scheduled(cron = "0 0/2 * * * 1-5")
	public void placeOrder() {
		if (!stockOrderQueue.isEmpty()) {
			final OrderJob job = stockOrderQueue.poll();

			System.out.println(" Placing order " + job.toString());

			final StockOrder order = new StockOrder();
			final Double price = job.getTriggerPrice();
			order.setPrice(price);
			order.setQuantity(300);
			order.setSymbolId(job.getSymbolId());
			order.setSymbolName(job.getSymbolName());
			order.setTradeType(TradeType.BUY);

			// calculate stop loss
			order.setStopLoss((price - price * 10) / 100);
			order.setStatus(TradeStatus.ACTIVE);

			orderRepository.save(order);

			// call the zerodha order placement service to live place orders
			// TODO:
		}
	}

	@Scheduled(cron = "0 0/2 * * * 1-5") //pull jobs every 2 minutes
	public void findJobs() {
		System.out.println(" Find orders available in the system ");
		final Set<OrderJob> unique = new HashSet<>();
		final List<OrderJob> orders = orderJobRepository.findAll()
				.stream().filter(e -> unique.add(e)).collect(Collectors.toList());
		try {
			if (!CollectionUtils.isEmpty(orders)) {
				orders.forEach(s -> stockOrderQueue.add(s));
			}
		} catch (Exception e) {
			System.out.println(" Can add only 10 orders " + e.getCause());
		}
	}

	@Scheduled(cron = "0 0/2 * * * 1-5") //pull orders every 5 minutes
	public void triggerOrder() {
		List<StockOrder> activeOrders = null;
		if (orders.isEmpty()) {
			orders.addAll(orderRepository.findAll());
		}
		if (!CollectionUtils.isEmpty(orders)) {
			activeOrders = orders.stream().filter(s -> s.getStatus().getStatus().equals(TradeStatus.ACTIVE.getStatus()))
					.collect(Collectors.toList());
		}
		if (!CollectionUtils.isEmpty(activeOrders)) {
			activeOrders.forEach(s -> activeOrderQueue.add(s));
		}
	}

	@Scheduled(cron = "0 0/1 * * * 1-5") //check whether stop loss is triggered, only when on 9.15am 
	public void executeOrder() {
		if (!activeOrderQueue.isEmpty()) {
			final StockOrder order = activeOrderQueue.poll();
			if (order != null) {
				final DataRequest request = new DataRequest();
				request.setSymbol(order.getSymbolId());
				request.setSymbolName(order.getSymbolName());
				request.setTimeframe("1minute");
				request.setFromDate(LocalDate.now().toString());
				request.setToDate(LocalDate.now().toString());
				
				final StockRequest stockRequest = new StockRequest(order.getTriggerPrice(), request);
				dataPoolingService.addToQueue(stockRequest);
				dataPoolingService.start();
			}
		}
	}
}
