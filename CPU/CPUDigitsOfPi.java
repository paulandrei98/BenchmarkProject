package CPU;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import Benchmark.*;


public class CPUDigitsOfPi implements IBenchmark{
	
	private PiCalculator pi;
	private int size;
	
	@Override
	public void initialize(int size) {
		this.pi = new PiCalculator(size);
		this.size=size;
	}
	
	
	@Override
	public void run() {
		
	}
	@Override
	public void run(Object... objects) {
		int option = (Integer)objects[0];
		
		switch(option) {
		case 1 :
			BigDecimal result= pi.call();
			//System.out.println(result);
		}
	}
	@Override
	public void warmUp() {
		this.run();
		
	}	


	@Override
	public void clean() {
		System.gc();
		
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
