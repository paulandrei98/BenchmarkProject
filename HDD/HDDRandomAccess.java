package HDD;

import Benchmark.IBenchmark;
import Timing.Timer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

public class HDDRandomAccess implements IBenchmark{

	private static File currentDir = new File("");	
	private final static String PATH = ""+currentDir.getAbsoluteFile()+"\\HDD";  // * INLOCUIESTE AICI CU NUMELE PROIECTULUI - in loc de HDD
	private String result;
	private double benchScore;
	
	public double getBenchScore() {
		return Double.parseDouble(String.format("%.2f", benchScore));
	}

	@Override
	public void initialize(int size) {
		RandomAccessFile file;

		try {
			file = new RandomAccessFile(PATH+"1", "rw");
			Random rand = new Random();
			int bufferSize = 1024;
			int toWrite = size / bufferSize;
			byte[] buffer = new byte[bufferSize];
			int counter = 0;

			while (counter++ < toWrite) {
				rand.nextBytes(buffer);
				file.write(buffer);
			}

			file.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public void run(Object ...option) {
		final int runtime = 5000; // ms
		Object[] param = (Object[]) option;

		try {
			
			if (String.valueOf(param[0]).toLowerCase().equals("r")) {
			
				int bufferSize = Integer.parseInt(String.valueOf(param[2]));
				if (String.valueOf(param[1]).toLowerCase().equals("fs")) {
					int steps = 1000;
					long timeMs = new RandomAccess().randomReadFixedSize(PATH+"1",
							bufferSize, steps);
					
					result = steps + " random reads in " + timeMs + " ms [" 
							+ (steps * bufferSize / 1024 / 1024) + " KB, "
							+ 1.0 * (steps * bufferSize / 1024 / 1024) / timeMs * 1000 + "MB/s]";
					benchScore = 1.0 * (steps * bufferSize / 1024 / 1024) / timeMs * 1000;
				}
				else if (String.valueOf(param[1]).toLowerCase().equals("ft")) {

					int ios = new RandomAccess().randomReadFixedTime(PATH+"1",
							Integer.parseInt(String.valueOf(param[2])), runtime);
					result = ios + " I/Os per second"+(ios * bufferSize / 1024 / 1024.04) + " KB, "
							+ 1.0 * (ios * bufferSize / 1024 / 1024.0) / runtime * 1000 + "MB/s]";
					benchScore = 1.0 * (ios * bufferSize / 1024 / 1024.0) / runtime * 1000;
				} else
					throw new UnsupportedOperationException("Read option \""
							+ String.valueOf(param[1])
							+ "\" is not implemented");

			}
		
			else if (String.valueOf(param[0]).toLowerCase().equals("w")) {
				int bufferSize = Integer.parseInt(String.valueOf(param[2]));
				
				int steps = 1000;
				long timeMs = new RandomAccess().randomReadFixedSize(PATH+"1",
						bufferSize, steps);
				if (String.valueOf(param[1]).toLowerCase().equals("fs")) {


					result = steps + " random reads in " + timeMs + " ms [" 
							+ (steps * bufferSize / 1024 / 1024) + " KB, "
							+ 1.0 * (steps * bufferSize / 1024 / 1024) / timeMs * 1000 + "MB/s]";
					benchScore = 1.0 * (steps * bufferSize / 1024 / 1024) / timeMs * 1000;
				}
				
				else if (String.valueOf(param[1]).toLowerCase().equals("ft")) {

					int ios = new RandomAccess().randomWriteFixedTime(PATH+"1",
							Integer.parseInt(String.valueOf(param[2])), runtime);
					result = ios + " I/Os per second "+(ios * bufferSize / 1024 / 1024.0) + " KB, "
							+ 1.0 * (ios * bufferSize / 1024 / 1024.0) / runtime * 1000 + "MB/s]";
					benchScore = 1.0 * (ios * bufferSize / 1024 / 1024.0) / runtime * 1000;
				}
			} else
				throw new UnsupportedOperationException("Benchmark option \""
						+ String.valueOf(param[0]) + "\" is not implemented");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clean() {
		System.gc();
	}

	public String getResult() {
		return String.valueOf(result);
	}

	class RandomAccess {
		private Random random;

		RandomAccess() {
			random = new Random();
		}

		public int randomWriteFixedTime(String filePath, int bufferSize,
				int millis) throws IOException {
	
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			
			int fileSize = (int) (file.getChannel().size() % Integer.MAX_VALUE);
			
			int counter = 0;
			
			byte[] bytes = new byte[bufferSize];
			Random rand = new Random();
			long now = System.nanoTime();
			
			while ((System.nanoTime() - now) < (millis * 1000000)) {
			
				file.seek(random.nextInt(fileSize));
				rand.nextBytes(bytes);
				file.write(bytes);
				counter++;
			}
			file.close();

			return counter;
		}

		public long randomWriteFixedSize(String filePath, int bufferSize,
				int toRead)  throws IOException {
		
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			int fileSize = (int) (file.getChannel().size() % Integer.MAX_VALUE);
			int counter = 0;
			byte[] bytes = new byte[bufferSize];
			Timer timer = new Timer();
			Random rand = new Random();
			
			timer.start();
			while (counter++ < toRead) {
				file.seek(random.nextInt(fileSize));
				rand.nextBytes(bytes);
				file.write(bytes);
			}

			file.close();
			return timer.stop() / 1000000; // ms
		}

		public long randomReadFixedSize(String filePath, int bufferSize,
				int toRead) throws IOException {
			
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			int fileSize = (int) (file.getChannel().size() % Integer.MAX_VALUE);
			int counter = 0;
			byte[] bytes = new byte[bufferSize];
		
			Timer timer = new Timer();

			timer.start();
			while (counter++ < toRead) {
				file.seek(random.nextInt(fileSize));
				file.read(bytes);
			}

			file.close();
			return timer.stop() / 1000000; // ms
		}

	
		public int randomReadFixedTime(String filePath, int bufferSize,
				int millis) throws IOException {
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");	
			int fileSize = (int) (file.getChannel().size() % Integer.MAX_VALUE);
			int counter = 0;	
			byte[] bytes = new byte[bufferSize];
			long now = System.nanoTime();
			
			while ((System.nanoTime() - now) < (millis * 1000000)) {
				file.seek(random.nextInt(fileSize));
				file.read(bytes);
				counter++;
			}
			file.close();

			return counter;
		}

		public byte[] readFromFile(String filePath, int position, int size)
				throws IOException {

			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			file.seek(position);
			byte[] bytes = new byte[size];
			file.read(bytes);
			file.close();
			return bytes;

		}

		public void writeToFile(String filePath, String data, int position)
				throws IOException {

			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			file.seek(position);
			file.write(data.getBytes());
			file.close();

		}
	}


	public void initialize(Object... params) {
	}

	
	public void cancel() {
		clean();	
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

	@Override
	public void warmUp() {
		// TODO Auto-generated method stub
		
	}
}
