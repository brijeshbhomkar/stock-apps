package com.data.service.dataservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.CandleTick;
import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.external.KiteDataService;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.technicalanalysis.MovingAverage;

@RestController
@RequestMapping("/api/ma")
public class MovingAverageController {

	private static final Logger log = LoggerFactory.getLogger(MovingAverageController.class);

	@Autowired
	private KiteDataService kiteDataService;

	@Autowired
	private SymbolRepository symbolRepository;

	@GetMapping("/{symbol}/{ma}")
	public ResponseEntity<?> movingAverage(@PathVariable final String symbol, @PathVariable final String ma) {
		final Symbol sym = symbolRepository.findSymbol(symbol);
		final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
		dataSearchCriteria.setKiteId("RB1822");
		dataSearchCriteria.setSymbol(symbol);
		dataSearchCriteria.setStartDate(LocalDate.now().minusDays(Long.parseLong(ma)).toString());
		dataSearchCriteria.setEndDate(LocalDate.now().toString());
		dataSearchCriteria.setPeriod("day");

		CandleResponse data = kiteDataService.get(dataSearchCriteria, sym.getSymbolId());
		final List<CandleTick> candleTicks = kiteDataService.extractData(data.getData(), sym.getSymbol(),
				dataSearchCriteria.getPeriod());
		final MovingAverage movingAverage = new MovingAverage(candleTicks.size());
		final List<Double> result = new ArrayList<Double>(1);
		candleTicks.forEach(o -> {
			result.add(movingAverage.next(o.getClose()));
		});
		return new ResponseEntity<List<Double>>(result, HttpStatus.OK);
	}

}
