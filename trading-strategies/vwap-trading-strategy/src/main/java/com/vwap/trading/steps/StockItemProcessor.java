package com.vwap.trading.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vwap.trading.models.GainerLoser;
import com.vwap.trading.models.JobDetails;
import com.vwap.trading.models.Symbol;
import com.vwap.trading.repositories.SymbolRepository;

@Component
public class StockItemProcessor implements ItemProcessor<StockReader, List<JobDetails>> {

	@Autowired
	private SymbolRepository symbolRepository;

	@Override
	public List<JobDetails> process(final StockReader item) throws Exception {
		final Map<String, List<GainerLoser>> mapLoserGainer = item.getMaps();
		final List<JobDetails> jobs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(mapLoserGainer)) {
			mapLoserGainer.entrySet().forEach(s -> {
				if (s.getKey().equals("LOSER")) {
					if (!CollectionUtils.isEmpty(s.getValue())) {
						s.getValue().forEach(l -> {
							Symbol symbol = null;
							try {
								symbol = symbolRepository.findSymbol(l.getSymbol());
								if (symbol != null) {
									final JobDetails job = new JobDetails();
									job.setSymbol(l.getSymbol());
									job.setSymbolId(symbol.getSymbolId());
									job.setTimeframe(symbol.getTimeframe());
									job.setUserId(symbol.getUserId());
									jobs.add(job);
								}
							} catch (Exception e) {
								System.out.println(" Failed to find symbol ");
							}
						});
					}
				} else if (s.getKey().equals("GAINER")) {
					if (!CollectionUtils.isEmpty(s.getValue())) {
						s.getValue().forEach(l -> {
							Symbol symbol = null;
							try {
								symbol = symbolRepository.findSymbol(l.getSymbol());
								if (symbol != null) {
									final JobDetails job = new JobDetails();
									job.setSymbol(l.getSymbol());
									job.setSymbolId(symbol.getSymbolId());
									job.setTimeframe(symbol.getTimeframe());
									job.setUserId(symbol.getUserId());
									jobs.add(job);
								}
							} catch (Exception e) {
								System.out.println("Failed to find symbol");
							}
						});
					}
				}
			});
		}
		return jobs;
	}

}
