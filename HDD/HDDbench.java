package HDD;

public class HDDbench extends Thread{
	private static HDDbench instance;
	private String result;
	private double finalScore;
	private HDDbench() {}
	
	static {
        instance = new HDDbench();
    }
	
	public static HDDbench getInstance() {
        return instance;
    }
	
	@Override
	public void run() {
		TestHDDWriteSpeed_FixedBuffer t1= new TestHDDWriteSpeed_FixedBuffer();
		TestHDDWriteSpeed_FixedSize t2= new TestHDDWriteSpeed_FixedSize();
		TestHDDRandomAccess_ReadFixedSize t3= new TestHDDRandomAccess_ReadFixedSize();
		TestHDDRandomAccess_ReadFixedTime t4= new TestHDDRandomAccess_ReadFixedTime();
		TestHDDRandomAccess_WriteFixedSize t5= new TestHDDRandomAccess_WriteFixedSize();
		TestHDDRandomAccess_WriteFixedTime t6= new TestHDDRandomAccess_WriteFixedTime();
		
		t1.start();
		try {
			t1.join();
		}catch(Exception e) {
			System.out.println(e);
		}

		
		t2.start();
		try {
			t2.join();
		}catch(Exception e) {
			System.out.println(e);
		}

		
		t3.start();
		try {
			t3.join();
		}catch(Exception e) {
			System.out.println(e);
		}


		t4.start();
		try {
			t4.join();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		t5.start();
		try {
			t5.join();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		t6.start();
		try {
			t6.join();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	/*	System.out.println("!!!!!"+t1.getBenchScore());
		System.out.println("!!!!"+t2.getBenchScore());
		System.out.println("!!!!"+t3.getBenchScore());
		System.out.println("!!!!"+t4.getBenchScore());*/
		finalScore = (t2.getBenchScore()+t1.getBenchScore()+t3.getBenchScore()+t4.getBenchScore()+t5.getBenchScore()+t6.getBenchScore()) / 6;
		//System.out.println();
		result ="HDD Score: "+(int)Math.floor(finalScore);
		
	}
	
	public String getScore() {return result;}
	
}
