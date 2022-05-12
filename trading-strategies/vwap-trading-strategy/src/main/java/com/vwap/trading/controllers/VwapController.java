package com.vwap.trading.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vwap")
public class VwapController {

//	@Autowired
//	private SymbolRepository symbolRepository;
//
//	@Autowired
//	private JobsRepository jobRepository;
//
//	@Autowired
//	@Qualifier("vwap")
//	private Job job;
//
//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Autowired
//	private JobOperator jobOperator;
//
//	@PostMapping("/{id}")
//	public ResponseEntity<?> startVwap(@PathVariable final String id) {
//		final Symbol symbol = symbolRepository.findSymbol(id);
//		if (symbol != null) {
//			Map<String, JobParameter> parameters = new HashMap<>();
//			parameters.put("symbol", new JobParameter(symbol.getSymbol()));
//			parameters.put("symbolId", new JobParameter(symbol.getSymbolId()));
//			parameters.put("timeframe", new JobParameter(symbol.getTimeframe()));
//			parameters.put("userId", new JobParameter(symbol.getUserId()));
//			JobParameters jobParameters = new JobParameters(parameters);
//			JobExecution jobExecution;
//			try {
//				jobExecution = jobLauncher.run(job, jobParameters);
//				return ResponseEntity.ok(jobExecution.getStatus());
//			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
//					| JobParametersInvalidException e) {
//				e.printStackTrace();
//			}
//		}
//		return ResponseEntity.ok(HttpStatus.OK);
//	}
//
//	@PostMapping("/stop")
//	public ResponseEntity<?> stopVwapJob() {
//		try {
//			Set<Long> executions = jobOperator.getRunningExecutions("vwap-job");
//			jobOperator.stop(executions.iterator().next());
//		} catch (NoSuchJobException | NoSuchJobExecutionException | JobExecutionNotRunningException e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(HttpStatus.OK);
//	}
//
//	@PostMapping("/{symbol}/{symbolId}/{userid}/{timeframe}")
//	public ResponseEntity<?> addVwap(@PathVariable final String symbol, @PathVariable final String symbolId,
//			@PathVariable final String userid, @PathVariable final String timeframe) {
//		final JobDetails jobDetails = new JobDetails();
//		jobDetails.setSymbol(symbol);
//		jobDetails.setSymbolId(symbolId);
//		jobDetails.setUserId(userid);
//		jobDetails.setTimeframe(timeframe);
//		jobRepository.save(jobDetails);
//		return ResponseEntity.ok(HttpStatus.OK);
//	}
}
