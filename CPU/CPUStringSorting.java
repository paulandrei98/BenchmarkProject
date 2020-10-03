package CPU;

import java.util.ArrayList;
import java.util.Collections;

import Benchmark.IBenchmark;

public class CPUStringSorting implements IBenchmark{
	
	static ArrayList<String> words = new ArrayList<String>();
	
	public CPUStringSorting(ArrayList<String> words) {
		CPUStringSorting.words= words;
	}
	
	@Override
	public void run(){
		quickSort(0,words.size()-1);
		//printWords();
	}

	@Override
	public void run(Object... options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clean() {
		System.gc();
	}

	@Override
	public void warmUp() {
		// TODO Auto-generated method stub
		
	}
	
	public void printWords() {
		for(int i=0; i<words.size(); i++)
			System.out.println(words.get(i));
	}
	
	private static void quickSort(int start, int end) {
		int i = start;
	    int j = end;
	    String pivot= words.get(start+(end-start)/2);
	    while (i<=j) {
	    	while ((words.get(i)).compareToIgnoreCase(pivot) < 0){
	    		i++;
	        }   
	        while ((words.get(j)).compareToIgnoreCase(pivot) > 0){
	        	j--;
	        }
	        if (i <= j) { 
	        	Collections.swap(words, i, j);
	        	i++;
	        	j--;
	        }
	     }
	     if(start<j)
	        quickSort(start, j);
	     if(i<end)
	        quickSort(i, end);
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
}