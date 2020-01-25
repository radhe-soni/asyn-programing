package com;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

/**
 * Future class represents a future result of an asynchronous computation � a
 * result that will eventually appear in the Future after the processing is
 * complete.
 * 
 *
 */
@Slf4j
public class FuturesExample
{
	SquareCalculator squareCalculator;
	public Long fetch(int integer)
	{
		Long result = 0L;
		Future<Long> futureValue = squareCalculator.submit(integer);
		try
		{
			doSomeTediuosTask();
			log.info("getting result from future");
			result = futureValue.get();
			log.info("result aquired {}", result);
		}
		catch (InterruptedException | ExecutionException e)
		{
			throw new RuntimeException("operation was not completed succesfully", e);
		}
		return result;
	}
	private void doSomeTediuosTask() throws InterruptedException {
		log.info("Doing a tediuos task");
		Thread.sleep(2000);
		log.info("Tediuos task completed");
	}
}
