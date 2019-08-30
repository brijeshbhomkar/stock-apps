package com.ema.trading.steps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ema.trading.jsons.Candles;
import com.ema.trading.jsons.Request;
import com.ema.trading.model.EMA15;
import com.ema.trading.model.EMA8;
import com.ema.trading.model.JobDetails;
import com.ema.trading.model.Symbol;
import com.ema.trading.service.DataPoolingService;
import com.ema.trading.service.EmaService;

@Component
@Qualifier("emaProcessor")
public class EmaProcessor implements ItemProcessor<List<Symbol>, List<JobDetails>> {

	@Autowired
	private DataPoolingService dataPoolingService;

	@Autowired
	private EmaService emaService;
	
	@Override
	public List<JobDetails> process(List<Symbol> items) throws Exception {
		final List<JobDetails> jobs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(items)) {
			items.forEach(s -> {
			final Request request = new Request();
			request.setSymbol(Long.toString(s.getSymbolId()));
			request.setSymbolName(s.getSymbol());
			request.setUserId(s.getUserId());
			request.setTimeframe(s.getTimeframe());
			request.setFromDate(LocalDate.now().minusDays(1).toString());
			request.setToDate(LocalDate.now().toString());
			final Candles candles = dataPoolingService.dataExchange(request);
			if (candles != null) {
				emaService.calculateEma(candles.getCandles(), request);
			}
			
			//check if ema cross over 
			final List<EMA8> ema8 = emaService.getEma8();
			final List<EMA15> ema15 = emaService.getEma15();
			int xlength = ema8.size();
			int ylength = ema15.size();
			int index = 0;
			while (index < xlength && index < ylength) {
				if (ema8.get(index).getEma8() > ema15.get(index).getEma15()) {
					final JobDetails jobDetails = new JobDetails();
					jobDetails.setSymbol(s.getSymbol());
					jobDetails.setSymbolId(s.getSymbolId());
					jobDetails.setTimeframe(s.getTimeframe());
					jobDetails.setUserId(s.getUserId());
					jobs.add(jobDetails);
				}
			}
		});
		}
		return jobs;
	}

}