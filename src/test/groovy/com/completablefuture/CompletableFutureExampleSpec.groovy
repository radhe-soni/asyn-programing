package com.completablefuture;

import java.util.concurrent.TimeUnit

import com.SquareCalculator;

import spock.lang.Specification

class CompletableFutureExampleSpec extends Specification {
	
	static SquareCalculator squareCalculator
	def setupSpec() {
		squareCalculator = new SquareCalculator(1)
	}
	def cleanupSpec() {
		squareCalculator.executor.shutdown();
		squareCalculator.executor.awaitTermination(5, TimeUnit.SECONDS);
	}
	def test(input1, input2, result){
		setup:
		CompletableFutureExample fixture = new CompletableFutureExample()
		fixture.squareCalculator = squareCalculator
		expect:
		result  == fixture.fetch(input1, input2) 
		where:
		input1 | input2 | result
		1677721 | 1677722 | [1544174961, 2384476310179351521, 1547530404, 2384476310179351521]
	}
}
