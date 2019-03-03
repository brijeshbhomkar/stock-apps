package com.stocks.data.service.controller;

import com.stocks.data.service.entity.Symbol;
import com.stocks.data.service.exception.ErrorResponse;
import com.stocks.data.service.repository.SymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The symbol controller to delete/update/edit new symbol for the stock.
 */
@RestController
@RequestMapping(value = "/api/v1/symbol")
public class SymbolController {

    private static final Logger LOG = LoggerFactory.getLogger(SymbolController.class);

    @Autowired
    private SymbolRepository symbolRepository;

    /**
     * Get the respective symbol based on symbol id.
     *
     * @param symbolId The symbol.
     * @return The symbol.
     */
    @RequestMapping(value = "/{symbolId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSymbol(@RequestParam final Long symbolId) {
        LOG.debug(" Get the symbol by symbol : {} ", symbolId);
        Symbol symbol = null;
        try {
            Optional<Symbol> optional = symbolRepository.findById(symbolId);
            if (optional.isPresent()) {
                symbol = optional.get();
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            LOG.error(" Failed to get the symbol : {} ", e);
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST + " : " + " Failed to get symbol ");
        }

        return ResponseEntity.ok(symbol);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestParam final Symbol symbol) {
        LOG.debug(" Add the symbol : {} ", symbol);
        try {
            symbolRepository.save(symbol);
        } catch (Exception e) {
            LOG.error(" Failed to add symbol : {} ", e);
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, " Failed to add symbol "));
        }
        return ResponseEntity.ok(" Created successfully ");
    }

    @RequestMapping(value = "/symbols", method = RequestMethod.GET)
    public ResponseEntity<?> findAllSymbols() {
        LOG.debug(" Find all the symbols ");
        List<Symbol> symbolList = null;
        try {
            symbolList = symbolRepository.findAll();
            if (CollectionUtils.isEmpty(symbolList)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            LOG.error(" Failed to find all the symbols : {} ", e);
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, " Failed to get all symbols "));
        }
        return ResponseEntity.ok(symbolList);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestParam final Symbol symbol) {
        LOG.debug(" update the symbol : {} ", symbol);
        try {
            if (symbolRepository.existsById(symbol.getId())) {
                symbolRepository.save(symbol);
            } else {
                return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, " Symbol doesn't exist : {} " + symbol));
            }
        } catch (Exception e) {
            LOG.error(" Failed to update the symbol : {} ", e);
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, " Failed to update symbol "));
        }
        return ResponseEntity.ok(" Updated successfully ");
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> servicePing() {
        return ResponseEntity.ok("Success");
    }

}
