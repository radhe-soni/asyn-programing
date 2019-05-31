package com;

import java.util.ArrayList;
import java.util.List;

public class ReduceExample
{
	String a;
	List<String> b;

	public ReduceExample(String a, List<String> b)
	{
		this.a=a;
		this.b=b;
	}
	public static void main(String[] args) {
		reduceExample();
	}
	public static void reduceExample() {
		ReduceExample firstAb = new ReduceExample("Mr. ", new ArrayList<>());
		firstAb.b.add("John");
		ReduceExample secondAb = new ReduceExample("Doe", new ArrayList<>());
		secondAb.b.add("C");
		ReduceExample thirdAb = new ReduceExample("Mr. ", new ArrayList<>());
		thirdAb.b.add("Doe");
		List<ReduceExample> abs = new ArrayList<>();
		abs.add(firstAb);
		abs.add(secondAb);
		abs.add(thirdAb);
		abs.stream().reduce((x,y)->{
			x.b.addAll(y.b);
			return x;
		}).ifPresent(ab -> System.out.println(ab.a+ab.b));
	}
}
