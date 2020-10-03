package CPU;

import java.util.ArrayList;

import Benchmark.IBenchmark;

public class CPUThreading implements IBenchmark {

	private int size;
	private int nThreads;
	
	@Override
	public void initialize(int size) {
		// TODO Auto-generated method stub
		this.size=size;
	}

	@Override
	public void initialize(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(Object ...objects) {
		this.nThreads= (Integer) objects[0];

		Thread[] threadsArr = new Thread[nThreads];
		for (int i = 0; i < nThreads; i++) {
			SQRTWorker worker = new SQRTWorker(i * size / nThreads, (i + 1)
					* size / nThreads);
			threadsArr[i] = new Thread(worker);
			threadsArr[i].start();
		}

		for (int i = 0; i < nThreads; i++) {
			try {
				threadsArr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void warmUp() {
		this.run(8);
		
	}



	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(ArrayList<String> words) {
		// TODO Auto-generated method stub
		
	}



}
