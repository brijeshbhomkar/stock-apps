package com.algo.trading.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.algo.trading.jsons.DataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algo.trading.core.RetracementService;
import com.algo.trading.entities.StockJob;
import com.algo.trading.entities.Symbol;
import com.algo.trading.repositories.RetracementRepository;
import com.algo.trading.repositories.StockJobRepository;
import com.algo.trading.repositories.SymbolRepository;
import com.algo.trading.services.SchedulingService;
import com.algo.trading.utils.TradeStatus;

@RestController
@RequestMapping("/api/trading")
public class ApplicationController {

	@Autowired
	private RetracementService retracementService;

	@Autowired
	private SymbolRepository symbolRepository;

	@Autowired
	private StockJobRepository jobService;
	
	@Autowired 
	private RetracementRepository retracementRepository;

	@Autowired
	private SchedulingService schedulingService;

	@PostMapping("/symbol/{timeframe}/{id}")
	public ResponseEntity<?> startActiveJob(@PathVariable final String timeframe, @PathVariable final String id) {
		final Symbol symbol = symbolRepository.findSymbol(id);
		if (symbol != null) {
			final DataRequest dataRequest = new DataRequest();
			dataRequest.setSymbolName(symbol.getSymbol());
			dataRequest.setSymbol(Long.toString(symbol.getSymbolId()));
			dataRequest.setTimeframe(timeframe);
			dataRequest.setUserId("RB1822");
			dataRequest.setFromDate(LocalDate.now().minusDays(6).toString());
			dataRequest.setToDate(LocalDate.now().minusDays(6).toString());
			//retracementService.process(Arrays.asList(dataRequest));
		}
		return ResponseEntity.ok("Started processing " + id);
	}

	@PostMapping("/add/{id}/{timeframe}")
	public ResponseEntity<?> addActiveJob(@PathVariable final String id, @PathVariable final String timeframe) {
		final Symbol symbol = symbolRepository.findSymbol(id);
		if (symbol != null) {
			final StockJob job = new StockJob();
			job.setSymbolId(Long.toString(symbol.getSymbolId()));
			job.setSymbol(symbol.getSymbol());
			job.setTimeframe(timeframe);
			job.setStatus("Active");
			jobService.saveAndFlush(job);
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/start/{id}")
	public ResponseEntity<?> startActiveJob(@PathVariable final String id) {
		final StockJob job = jobService.findStockJobById(id);
		if (job != null && job.getStatus() != null && job.getStatus().equals(TradeStatus.ACTIVE.getStatus())) {
			schedulingService.start();
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/all/{timeframe}")
	public ResponseEntity<?> startNiftyAll(@PathVariable final String timeframe) {
		final List<Symbol> symbols = symbolRepository.findAll();
		if (!CollectionUtils.isEmpty(symbols)) {
			symbols.forEach(s -> {
				final StockJob job = new StockJob();
				job.setSymbolId(Long.toString(s.getSymbolId()));
				job.setSymbol(s.getSymbol());
				job.setTimeframe(timeframe);
				job.setStatus(TradeStatus.ACTIVE.getStatus());
				jobService.delete(job);
				jobService.save(job);
			});
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PostMapping("/retracement/{date}/{timeframe}")
	public ResponseEntity<?> calculateRetracement(@PathVariable final String timeframe, @PathVariable final String date) {
		retracementRepository.deleteAll();
		retracementService.retracementCalculation(timeframe, date);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
