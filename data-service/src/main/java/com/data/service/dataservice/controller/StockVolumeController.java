package com.data.service.dataservice.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.VolumeGainerData;
import com.data.service.dataservice.external.VolumeGainerService;
import com.data.service.dataservice.json.VolumeGainer;
import com.data.service.dataservice.repository.VolumeGainerRepository;

@RestController
@RequestMapping("/api/nse/volume")
public class StockVolumeController {

	private static final Logger log = LoggerFactory.getLogger(StockVolumeController.class);

	@Autowired
	private VolumeGainerRepository repository;

	@Autowired
	private VolumeGainerService service;

	@GetMapping
	public ResponseEntity<?> volumeGainer() {
		log.debug("Find the volume gainer");
		List<VolumeGainer> volumeGainers = new ArrayList<VolumeGainer>();
		try {
			volumeGainers = service.findVolumeGainers();
			if (!CollectionUtils.isEmpty(volumeGainers)) {
				repository.saveAll(copy(volumeGainers));
			} else {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.debug("Failed to find volume gainers");
			return ResponseEntity.badRequest().body("Failed to find volume gainers");
		}

		return ResponseEntity.ok(volumeGainers.stream().sorted(Comparator.comparing(VolumeGainer::getTurnoverLakhs).reversed())
				.collect(Collectors.toList()));
	}

	private List<VolumeGainerData> copy(final List<VolumeGainer> data) {
		final List<VolumeGainerData> result = new ArrayList<VolumeGainerData>();
		for (VolumeGainer volumeGainer : data) {
			final VolumeGainerData volumeGainerData = new VolumeGainerData();
			BeanUtils.copyProperties(volumeGainer, volumeGainerData);
			result.add(volumeGainerData);
		}
		return result;
	}
}
