package com.data.service.dataservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.SymbolResponse;
import com.data.service.dataservice.util.SymbolMapper;

@RestController
@RequestMapping("/api/symbol")
public class SymbolController {

	private static final Logger logger = LoggerFactory.getLogger(SymbolController.class);

	@Autowired
	private SymbolRepository repository;

	@RequestMapping(value = "/nifty50", method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		try {
			SymbolMapper.getSymbolMapper().entrySet().forEach(s -> {
				final Symbol symbol = new Symbol();
				symbol.setExchange("nse");
				symbol.setSymbol(s.getKey());
				symbol.setSymbolId(Long.parseLong(s.getValue()));
				repository.saveAndFlush(symbol);
			});
		} catch (Exception e) {
			logger.error("Failed to add symbols");
		}
		return ResponseEntity.ok("sucess");
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody final Symbol symbol) {
		logger.debug(" Save the symbol : {} ", symbol);
		try {
			repository.saveAndFlush(symbol);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to save symbol");
		}
		return ResponseEntity.ok(symbol);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable final String id) {
		logger.debug(" Get the symbol : {} ", id);
		Symbol symbol = null;
		try {
			Optional<Symbol> result = repository.findById(Long.parseLong(id));
			if (result == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}

			if (result.isPresent()) {
				symbol = result.get();
			}
		} catch (Exception e) {
			logger.debug(" Failed to get the symbol : {} ", e);
			return ResponseEntity.badRequest().body(" Failed to get symbol ");
		}

		return ResponseEntity.ok(symbol);
	}

	@GetMapping(value = "/")
	public ResponseEntity<?> get() {
		logger.debug("Get all the symbols ");
		List<Symbol> symbols = null;
		try {
			symbols = repository.findAll();
			if (CollectionUtils.isEmpty(symbols)) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error("Failed to get the symbols", e);
			return ResponseEntity.badRequest().body("Failed to get symbols ");
		}
//		return ResponseEntity.ok(symbols.stream().map(Symbol::getSymbol).sorted()
//				.collect(Collectors.toList()).toArray());
		return ResponseEntity.ok(new SymbolResponse(symbols));
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody final Symbol symbol) {
		logger.debug(" Update the symbol : {} ", symbol);
		try {
			repository.saveAndFlush(symbol);
		} catch (Exception e) {
			logger.error("Failed to update symbol ", e);
			return ResponseEntity.badRequest().body("Failed to update symbols ");
		}

		return ResponseEntity.ok(symbol);
	}
}
