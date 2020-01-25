package com;

import java.math.BigInteger;
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

	public Long calculate(Integer input){
		sleep(1);
		Long result = (long) (input * input);
		log.info("exiting calculate");
		return result;
	}

	public BigInteger calculate(Long input){
		sleep(2);
		BigInteger result = BigInteger.valueOf(input).multiply(BigInteger.valueOf(input));
		log.info("exiting calculate");
		return result;
	}

	private void sleep(long seconds) {
		log.info("executing calculate asynchronously in");
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.error("My sleep was interrupted");
		}
	}
}