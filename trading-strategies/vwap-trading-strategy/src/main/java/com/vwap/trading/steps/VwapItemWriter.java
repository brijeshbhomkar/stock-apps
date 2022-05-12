package com.vwap.trading.steps;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vwap.trading.models.Vwap;
import com.vwap.trading.repositories.VwapRepository;

@Component
public class VwapItemWriter implements ItemWriter<Vwap> {

	@Autowired
	private VwapRepository vwapRepository;

	@Override
	public void write(final List<? extends Vwap> items) throws Exception {
		vwapRepository.saveAll(items);
	}

}
