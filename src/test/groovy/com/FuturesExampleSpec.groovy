package com


import static org.junit.Assert.*

import java.util.concurrent.TimeUnit

import spock.lang.Specification

class FuturesExampleSpec extends Specification {
	
	static SquareCalculator squareCalculator
	def setupSpec() {
		squareCalculator = new SquareCalculator(1)
	}
	def cleanupSpec() {
		squareCalculator.executor.shutdown();
		squareCalculator.executor.awaitTermination(5, TimeUnit.SECONDS);
	}
	def test(input, result){
		setup:
		FuturesExample fixture = new FuturesExample()
		fixture.squareCalculator = squareCalculator
		expect:
		result  == fixture.fetch(input) 
		where:
		input | result
		1677721 | 1544174961
	}
}
