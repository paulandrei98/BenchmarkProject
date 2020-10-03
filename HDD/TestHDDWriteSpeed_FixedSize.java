package HDD;

import HDD.HDDWriteSpeed;

public class TestHDDWriteSpeed_FixedSize extends Thread{
	private double benchScore;
	
	public double getBenchScore() {
		return benchScore;
	}
	
	public void run() {
			HDDWriteSpeed bench = new HDDWriteSpeed();
			bench.run("fs",true);
			benchScore = bench.getBenchScore();
			bench.clean();
			
			try {
				Thread.sleep(10);
			}catch(Exception e) {
				System.out.println(e);
			}
	}
}

