package HDD;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import Benchmark.IBenchmark;

public class HDDWriteSpeed implements IBenchmark{
	private double benchScore;
	public void initialize(Object... params) {
	}

	public double getBenchScore() {
		return benchScore;
	}
	public void run(Object... options) {
		FileWriter writer = new FileWriter();
		String option = (String) options[0];
		Boolean clean = (Boolean) options[1];
		
		File currentDir = new File("");
       // System.out.println("Working Directory : " + currentDir.getAbsoluteFile()); 
		String prefix = ""+currentDir.getAbsoluteFile()+"\\HDD"; // * INLOCUIESTE AICI CU NUMELE PROIECTULUI - in loc de HDD
		String suffix = ".dat";
		int startIndex = 0;
		int endIndex = 8;
		long fileSize = 1024 * 1024 * 512; //MB
		int bufferSize =1024 * 1; //  KB
		
		try {
			if (option.equals("fs")) {
				writer.streamWriteFixedSize(prefix, suffix, startIndex,
						endIndex, fileSize, clean);
				benchScore = writer.getBenchScore();
				}
			else if (option.equals("fb")) {
				writer.streamWriteFixedBuffer(prefix, suffix, startIndex,
						endIndex, bufferSize, clean);
				benchScore = writer.getBenchScore();
			}
			else
				throw new IllegalArgumentException("Argument "
						+ options[0].toString() + " is undefined");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void clean() {
		System.gc();
	}

	public String getResult() {
		return null;
	}

/*
	public void warmUp() {
		throw new UnsupportedOperationException(
				"Method not implemented yet. Use warmUp(Object) instead.");
	}
*/
	public void warmUp() {
		
	}
	public void cancel() {
		clean();	
	}

	@Override
	public void initialize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(ArrayList<String> words) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
