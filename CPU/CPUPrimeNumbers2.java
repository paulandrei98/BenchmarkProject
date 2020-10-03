package CPU;

import java.util.ArrayList;

import Benchmark.*;

public class CPUPrimeNumbers2 implements IBenchmark{
	private long N;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(Object... options) {
		if (options[0] instanceof String){
            String option=(String)options[0];
            switch (option) {
                case "primeNumbers":
                    this.primeNumbers();
                    break;
                default:
                    throw new IllegalArgumentException("Option invalid!");
            }
		}
	}

	private void primeNumbers() {
		long number=2;
		long primeCount=0;
		while(primeCount<N) {
			if(isPrime(number)) {
				//System.out.print(number + " ");
				primeCount++;
			}
			number++;
		}
	}
	
	private static boolean isPrime(long x) {
		if(x==0 || x==1)
			return false;
		for(int i=2; i<=x/2; i++) {
			if(x%i==0)
				return false;
		}
		return true;
	}
	

	@Override
	public void clean() {
		System.gc();
	}

	@Override
	public void warmUp() {
		this.run("primeNumbers");
	}

	@Override
	public void initialize(int size) {
		this.N=size;
		
	}

	@Override
	public void initialize(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(ArrayList<String> words) {
		// TODO Auto-generated method stub
		
	}

}
