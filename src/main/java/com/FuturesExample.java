package com;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Future class represents a future result of an asynchronous computation – a
 * result that will eventually appear in the Future after the processing is
 * complete.
 * 
 *
 */
public class FuturesExample
{
	public static void main(String[] args)
	{
		SquareCalculator squareCalculator = new SquareCalculator();
		Future<Integer> futureValue = squareCalculator.calculate(12);
		try
		{
			squareCalculator.executor.shutdown();
			System.out.println(futureValue.get());
			System.out.println("main: " + Thread.currentThread());
			squareCalculator.executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException | ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SquareCalculator
{

	ExecutorService executor = Executors.newSingleThreadExecutor();

	public Future<Integer> calculate(Integer input)
	{
		return executor.submit(() -> {
			System.out.println("calculate: " + Thread.currentThread());
			Thread.sleep(1000);
			return input * input;
		});
	}
}
