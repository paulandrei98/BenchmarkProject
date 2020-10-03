package HDD;

import HDD.HDDWriteSpeed;

public class TestHDDWriteSpeed_FixedBuffer extends Thread{
	private double benchScore;
	
	public double getBenchScore() {
		return benchScore;
	}
	public void run() {
		HDDWriteSpeed bench = new HDDWriteSpeed();
		bench.run("fb",true);
		benchScore = bench.getBenchScore();
		bench.clean();
		
		try {
			Thread.sleep(10);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
