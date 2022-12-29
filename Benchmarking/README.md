# Assignment 2 Part 1 - Benchmarking
Braden Saunders, Student # 6283725

## Throughput Benchmarks
The results (Benchmark_Output.txt file) indicate that the LinkedHashSet is the fastest at inserting, with 8 operations per second, the HashSet is a close second at ~6 operations per second, and TreeSet is the slowest at only ~3 operations per second.
The search (contains) test is very close between all 3 implementations, the HashSet appears to be slightly faster than the others, but taking the error +/- into consideration, the results of all 3 are statistically tied.
Sorting sets isn't something that can be easily done, because a HashSet and LinkedHashSet are defined as unordered, and a TreeSet is automatically sorted when items are inserted, nevertheless, the benchmarks show that HashSet and LinkedHashSet are essentially tied (with HashSet being slightly faster), and TreeSet being significantly (over 2x) faster.

## Average Time Benchmarks
The results of the average time benchmarks match that of the throughput benchmarks, indicating that the LinkedHashSet is the fastest to insert, taking only 0.123 seconds per operation, HashSet is a close second, taking 0.160 seconds per option, and TreeSet is the slowest, taking 0.350 seconds per operation.
The results for the other tests (search and sort) are so close to 0 (10^-7) that they are not very meaningful.

The sample time benchmarks, and single shot benchmarks echo the average time benchmarks almost exactly.
