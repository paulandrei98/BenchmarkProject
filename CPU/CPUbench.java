package CPU;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JLabel;

import Benchmark.IBenchmark;
import Timing.ITimer;
import Timing.Timer;
import userInterface.UI;

public class CPUbench extends Thread{
	private static CPUbench instance;
	private String result;

	static {
        instance = new CPUbench();
    }
	
	public static CPUbench getInstance() {
        return instance;
    }
	
	private CPUbench() {}
	
	
	@Override
	public void run() {
		
		ITimer timer = new Timer();
		long time = 0;
		int size;
		double finalScore;
		double scoreHuffman=0;
		double scoreSortingStrings=0;
		double scorePrimeNumber=0;
		double scoreDigitsOfPi=0;
		double scoreThreading=0;
		BufferedReader br;
		InputStream input;
		
		
		
		/*****COMPRESSION TEST -HUFFMAN*****/ //Adina
		IBenchmark bench = new CPUCompression();
		
		ArrayList<String> words2= new ArrayList<String>();
		String line2;
		try {
			input = getClass().getResourceAsStream("/image/words3");
			br = new BufferedReader(new InputStreamReader(input)); 
			while ((line2 = br.readLine()) != null) {
				words2.add(line2);
			}
			br.close();
		}catch(IOException e) {
				e.printStackTrace();
		}

		bench.initialize(words2);
		size = 100000;
		for(int i=0; i<5; i++) {
			timer.start();
			bench.run(1);
			time=timer.stop();
			scoreHuffman += size/(time/Math.pow(10, 9));
			bench.clean();
		}
		scoreHuffman = scoreHuffman / 5;
		
		
		
		
		
		/*****String Sorting*****/
		ArrayList<String> words= new ArrayList<String>(); //Adina
		String line;
		try {
			input = getClass().getResourceAsStream("/image/adina.txt");
			br = new BufferedReader(new InputStreamReader(input)); 
			while ((line = br.readLine()) != null) {
				words.add(line);
			}
		}catch(IOException e) {
				e.printStackTrace();
		}
		
		size=817009;
		CPUStringSorting bench5= new CPUStringSorting(words);
		for(int i=0; i<5; i++) {
			timer.start();
			bench5.run();
			time=timer.stop();
			scoreSortingStrings += size/(time/Math.pow(10, 9));
			bench5.clean();
		}
		scoreSortingStrings = Math.sqrt(scoreSortingStrings / 5);
	    
	
		
		
		
		/*****PRIME NUMBERS TEST*****/
		CPUPrimeNumbers2 bench3 =  new CPUPrimeNumbers2();
		time = 0;
		size=20000;
		bench3.initialize(size);
		
		for(int i=0; i<5; i++) {
			timer.start();
			bench3.run("primeNumbers"); //Adina
			time=timer.stop();
			scorePrimeNumber += size/(time/Math.pow(10, 9));
			bench3.clean();
		}
		scorePrimeNumber = scorePrimeNumber / 5;
		
		
		
		
		
		/***** DIGITS OF PI TEST*****/ //Paul
		CPUDigitsOfPi bench4 = new CPUDigitsOfPi();
		
		int digits = 100000;
		bench4.initialize(digits);
		time = 0;
		
		for(int i=0; i<5; i++) {
			timer.start();
			bench4.run(1);
			time = timer.stop();
			scoreDigitsOfPi += digits/(time/Math.pow(10, 9));
			bench4.clean();
		}
		scoreDigitsOfPi /= 5;
		
		
		
		
		
		
		/***** MULTY THREADING TEST*****/ //Paul
		CPUThreading bench6 = new CPUThreading();
		time = 0;
		
		int workload = 50000000;
		bench6.initialize(workload);
		
		bench6.warmUp();
		

			for(int i=1; i<=32; i*=2)
			{
				timer.start();
				bench6.run(i);
				time = timer.stop();
			
				scoreThreading += workload / ((time/Math.pow(10, 9)) * i);
			}
		bench6.clean();
		scoreThreading = Math.sqrt(scoreThreading / 6);
		
		
		finalScore = (scoreHuffman + scoreSortingStrings + scorePrimeNumber + scoreDigitsOfPi + scoreThreading) /5;
		finalScore = Math.floor(finalScore);
		result ="CPU Score: "+ (int)finalScore;
		
	}
	
	public String getScore() {return result;}

}
