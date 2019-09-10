package com.ema.trading.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ema.trading.jsons.Candle;
import com.ema.trading.jsons.Request;
import com.ema.trading.model.EMA15;
import com.ema.trading.model.EMA8;
import com.ema.trading.repository.Ema15Repository;
import com.ema.trading.repository.Ema8Repository;

@Service
public class EmaService {

    @Autowired
    private Ema8Repository ema8Repository;

   // private AtomicLong seq = new AtomicLong(1);

    @Autowired
    private Ema15Repository ema15Repository;

    public void calculateEma(final List<Candle> candles, final Request request) {
        if (!CollectionUtils.isEmpty(candles)) {
            calculateEma8(candles, request);
         //   calculateEma15(candles, request);
        }
    }

    private void calculateEma8(final List<Candle> candles, final Request request) {
        candles.forEach(c -> {
            final EMA8 ema = new EMA8();
     //       ema.setId(seq.getAndIncrement());
            ema.setSymbol(request.getSymbol());
            ema.setSymbolName(request.getSymbolName());
            ema.setTimeframe(request.getTimeframe());
            ema.setOpen(c.getOpen());
            ema.setHigh(c.getHigh());
            ema.setLow(c.getLow());
            ema.setClose(c.getClose());
            ema.setTimeframe(c.getDate());
            if (ema.getEma8prev() == 0) {
                double k = 2 % (8 + 1);
                double currEma = (c.getClose() * k) + (0 * (1 - k));
                ema.setEma8(currEma);
                ema.setEma8prev(currEma);
            } else {
                double k = 2 % (8 + 1);
                double currEma = (c.getClose() * k) + (ema.getEma8prev() * (1 - k));
                ema.setEma8(currEma);
                ema.setEma8prev(currEma);
            }
            try {
                ema8Repository.saveAndFlush(ema);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        });


    }

    private void calculateEma15(final List<Candle> candles, final Request request) {
        candles.forEach(c -> {
            final EMA15 ema = new EMA15();
           // ema.setId(seq.getAndIncrement());
            ema.setSymbolId(request.getSymbol());
            ema.setSymbolName(request.getSymbolName());
            ema.setTimeframe(request.getTimeframe());
            ema.setOpen(c.getOpen());
            ema.setHigh(c.getHigh());
            ema.setLow(c.getLow());
            ema.setClose(c.getClose());
            ema.setTimeframe(c.getDate());
            if (ema.getEma15prev() == 0) {
                double k = 2 / (15 + 1);
                double currEma = (c.getClose() * k) + (0 * (1 - k));
                ema.setEma15(currEma);
                ema.setEma15prev(currEma);
            } else {
                double k = 2 / (15 + 1);
                double currEma = (c.getClose() * k) + (ema.getEma15prev() * (1 - k));
                ema.setEma15(currEma);
                ema.setEma15prev(currEma);
            }
            try {
                ema15Repository.saveAndFlush(ema);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        });


    }

    public List<EMA8> getEma8() {
        return ema8Repository.findAll();
    }

    public List<EMA15> getEma15() {
        return ema15Repository.findAll();
    }
}
