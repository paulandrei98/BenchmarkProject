package Benchmark;

import java.util.ArrayList;

public interface IBenchmark {
	public void initialize(int size);
	public void initialize(String str);
	public void initialize(ArrayList<String> words);
	public void run();
	public void run(Object ...objects);
	public void warmUp();
	public void clean();
}
