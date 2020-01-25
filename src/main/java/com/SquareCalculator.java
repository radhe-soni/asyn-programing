package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SquareCalculator {

	ExecutorService executor;

	public SquareCalculator(int parallelism) {
		log.info("SquareCalculator instantiated");
		executor = Executors.newFixedThreadPool(parallelism);
	}

	public Future<Long> submit(Integer input) {
		return executor.submit(() -> calculate(input));
	}

	public Long calculate(Integer input) throws InterruptedException {
		log.info("executing calculate asynchronously in");
		Thread.sleep(1000);
		Long result = (long) (input * input);
		log.info("exiting calculate");
		return result;
	}
}