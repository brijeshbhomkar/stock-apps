package com.ema.trading.service;

import com.ema.trading.model.EmaJob;
import com.ema.trading.repository.EmaJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class JobEnablerService {

    @Autowired
    private EmaJobRepository emaJobRepository;

    @Autowired
    private EmaService emaService;

    private BlockingQueue<EmaJob> jobQueues = new ArrayBlockingQueue<>(50);

    public void addJob(final String symbol, final String symbolName, final String timeframe) {
        final EmaJob job = new EmaJob();
        job.setSymbol(symbol);
        job.setSymbolName(symbolName);
        job.setTimeframe(timeframe);
        emaJobRepository.save(job);
    }

    @Scheduled(cron = "")
    public void findJobs() {
        final List<EmaJob> jobs = emaJobRepository.findAll();
        if (!CollectionUtils.isEmpty(jobs)) {
            jobQueues.addAll(jobs);
        }
    }

    @Scheduled(cron="")
    public void startJob() {
        if (!jobQueues.isEmpty()) {
            //submit jobs
            final EmaJob job = jobQueues.poll();

        }
    }
}
