package HDD;


public class TestHDDRandomAccess_WriteFixedTime extends Thread{
		private double benchScore;
	
		public double getBenchScore() {
			return benchScore;
		}
	
		public void run() {
			HDDRandomAccess bench = new HDDRandomAccess();
			bench.initialize(1024 * 1024 * 1024L); // 1 GB
			
			bench.run(new Object[] {"w", "ft", 4*1024});// 4 KB
			benchScore = bench.getBenchScore();
			bench.clean();
			
			try {
				Thread.sleep(10);
			}catch(Exception e) {
				System.out.println(e);
			}
			/*
			bench.run(new Object[] {"w", "ft", 64*1024});// 64 KB
			System.out.println(bench.getResult());
			bench.clean();
			
			try {
				Thread.sleep(10);
			}catch(Exception e) {
				System.out.println(e);
			}
			*/
			bench.run(new Object[] {"w", "ft", 1024*1024}); // 1 MB 
			//System.out.println(bench.getResult());
			bench.clean();
			
			try {
				Thread.sleep(10);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}