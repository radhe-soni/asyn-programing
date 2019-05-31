package com;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureAndHigherOrderFunctions
{
	static AtomicInteger ai = new AtomicInteger(5);

	public static String firstMethod(String msg) {
		return "first method : " + msg;
	}
	
	public static String secondMethod(String msg) {
		return "second method : " + msg;
	}
	
	public static String thirdMethod(String msg) {
		return "third method : " + msg;
	}
	
	public static void main(String[] args)
	{
		
		String sample = "Hello!!!";
		
		System.out.println("Calling suppliers");
		List<String> resultedStrings = supplierExample(sample);
		System.out.println(resultedStrings);
		addSeparator();
		sleep(2000);
		System.out.println("Calling operators");
		List<String> operatedStrings = getOperatedStrings(sample);
		System.out.println(operatedStrings);
		

	}

	private static void addSeparator()
	{
		System.out.println();
		System.out.println();
		IntStream.range(0, 100).forEach(i -> System.out.print("*"));
		System.out.println();
		IntStream.range(0, 100).forEach(i -> System.out.print("*"));
		System.out.println();
		System.out.println();
	}

	private static List<String> getOperatedStrings(String sample)
	{
		ai.set(5);
		CompletableFuture<String> future = CompletableFuture.completedFuture(sample);
		List<Function<String, String>> stringFunctions = new ArrayList<>();
		stringFunctions.add(wait(CompletableFutureAndHigherOrderFunctions::firstMethod));
		System.out.println("Checkpoint 1");
		stringFunctions.add(wait(CompletableFutureAndHigherOrderFunctions::secondMethod));
		System.out.println("Checkpoint 2");
		stringFunctions.add(wait(CompletableFutureAndHigherOrderFunctions::thirdMethod));
		System.out.println("Checkpoint 3");
		List<CompletableFuture<String>> futures = stringFunctions.stream().map(fun -> future.thenApplyAsync(fun))
				.collect(Collectors.toList());
		List<String> operatedStrings = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
				.thenApply(v -> futures.stream().map(fut -> fut.join())
						.peek(str -> System.out.println(LocalDateTime.now().getNano() + " Function Completed " + str)).collect(Collectors.toList()))
				.join();
		return operatedStrings;
	}

	private static Function<String, String> wait(Function<String, String> stringFunction)
	{
		return string -> {
			try
			{
				System.out.println(string + " " + Thread.currentThread());
				Thread.sleep(ai.getAndDecrement() * 1000);
				System.out.println(stringFunction.apply(string));
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return stringFunction.apply(string);
		};
	}

	private static List<String> supplierExample(String sample)
	{
		ai.set(5);
		List<Supplier<String>> stringFunctions2 = new ArrayList<>();
		stringFunctions2.add(supplier(firstMethod(sample)));
		stringFunctions2.add(supplier(secondMethod(sample)));
		stringFunctions2.add(supplier(thirdMethod(sample)));
		List<CompletableFuture<String>> collect = stringFunctions2.stream()
				.map(supplier -> CompletableFuture.supplyAsync(supplier))
				.collect(Collectors.toList());
		List<String> resultedStrings = CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
				.thenApply(v -> collect.stream().map(fut -> fut.join())
						.peek(str -> System.out.println(LocalDateTime.now().getNano() + " Supplier Completed " + str)).collect(Collectors.toList()))
				.join();
		return resultedStrings;
	}

	private static Supplier<String> supplier(String string)
	{
		int andDecrement = ai.getAndDecrement();
		return () -> {
			try
			{
				System.out.println(string +  " " + Thread.currentThread());
				Thread.sleep(andDecrement * 1000);
				System.out.println(string);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return string;
		};
	}
	
	private static void sleep(long duration)
	{
			try
			{
				Thread.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}