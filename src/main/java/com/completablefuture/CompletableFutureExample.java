package com.completablefuture;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

import com.SquareCalculator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletableFutureExample {
	SquareCalculator squareCalculator;

	public List<Number> fetch(Integer integer1, Integer integer2) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool(2);
		CompletableFuture<Long> cmnPoolFuture = CompletableFuture
				.supplyAsync(() -> squareCalculator.calculate(integer1));

		CompletableFuture<BigInteger> cmnPoolFtrWithRsltSquared = getCmnPoolFtrWithResultSquared(integer1);
		CompletableFuture<Long> futureInSeparatePool = CompletableFuture
				.supplyAsync(() -> squareCalculator.calculate(integer2), pool);

		CompletableFuture<BigInteger> ftrInSeparatePoolWithRsltSquared = getFtrInSeparatePoolWithRsltSquared(integer1,
				pool);
		doSomeTediuosTask();
		List<Number> values = completeAllFutureSequencially(cmnPoolFuture, cmnPoolFtrWithRsltSquared,
				futureInSeparatePool, ftrInSeparatePoolWithRsltSquared);
		System.out.println(values);
		return values;
	}

	private CompletableFuture<BigInteger> getFtrInSeparatePoolWithRsltSquared(Integer integer1, ForkJoinPool pool) {
		CompletableFuture<BigInteger> futureInSeparatePoolWithResultSquared = CompletableFuture
				.supplyAsync(() -> squareCalculator.calculate(integer1), pool)
				.thenApplyAsync(squareCalculator::calculate, pool);
		return futureInSeparatePoolWithResultSquared;
	}

	private CompletableFuture<BigInteger> getCmnPoolFtrWithResultSquared(Integer integer1) {
		CompletableFuture<BigInteger> commonPoolFutureWithResultSquared = CompletableFuture
				.supplyAsync(() -> squareCalculator.calculate(integer1)).thenApplyAsync(squareCalculator::calculate);
		return commonPoolFutureWithResultSquared;
	}

	private void doSomeTediuosTask() throws InterruptedException {
		log.info("Doing a tediuos task");
		Thread.sleep(1000);
		log.info("Doing a tediuos task");
		Thread.sleep(1000);
		log.info("Doing a tediuos task");
		Thread.sleep(1000);
		log.info("Doing a tediuos task");
		Thread.sleep(1000);
		log.info("Tediuos task completed");
	}

	private static List<Number> completeAllFutureSequencially(CompletableFuture<Long> completableFuture1,
			CompletableFuture<BigInteger> completableFuture2, CompletableFuture<Long> completableFuture3,
			CompletableFuture<BigInteger> completableFuture4) {
		return Arrays.asList(completableFuture1.join(), completableFuture2.join(), completableFuture3.join(),
				completableFuture4.join());
	}
}
