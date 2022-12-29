package me.braden.Benchmarking;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkHashSet {
	
	@State(Scope.Benchmark)
	public static class Params {
		public Set<Integer> hashSet = new HashSet<>();
		public int numElementsToInsert = 5000000;
		public int searchValue = 561453;
		
	}
	
	@Benchmark
	public void benchmarkInsert(Params params) {
		for(int i = 0; i < params.numElementsToInsert; i++) {
			params.hashSet.add(i);
		}
	}
	
	@Benchmark
	public void benchmarkSort(Params params) {
		params.hashSet.stream().sorted().collect(Collectors.toSet());
	}
	
	@Benchmark
	public void benchmarkSearch(Params params) {
		params.hashSet.contains(params.searchValue);
	}
	
	public static void main(String...args) throws IOException {
		org.openjdk.jmh.Main.main(args);
	}

}
