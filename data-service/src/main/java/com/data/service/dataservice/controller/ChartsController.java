package com.data.service.dataservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.external.KiteDataService;
import com.data.service.dataservice.json.Candle;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.util.RestfulSupport;

@RestController
@RequestMapping("/api/nse")
public class ChartsController extends RestfulSupport {

	@Autowired
	private SymbolRepository symbolRepository;

	@Autowired
	private KiteDataService kiteDataService;

	@CrossOrigin
	@GetMapping("/symbol/{id}")
	public ResponseEntity<?> getStockDate(@PathVariable final String id) {
		List<Candle> candles = null;
		try {
			if (id != null && !id.isEmpty()) {
				final Symbol symbol = symbolRepository.findSymbol(id);
				final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
				dataSearchCriteria.setKiteId("RB1822");
				dataSearchCriteria.setPeriod("day");
				dataSearchCriteria.setStartDate(LocalDate.now().minusYears(3).toString());
				dataSearchCriteria.setEndDate(LocalDate.now().toString());
				CandleResponse data = kiteDataService.get(dataSearchCriteria, symbol.getSymbolId());
				candles = data.getData().getCandles();
				if (CollectionUtils.isEmpty(candles)) {
					return ResponseEntity.badRequest().body("No data found!" + HttpStatus.NO_CONTENT);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to find the data " + HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(candles);
	}
}
