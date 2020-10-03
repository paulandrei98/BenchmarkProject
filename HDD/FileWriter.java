package HDD;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import Timing.Timer;

public class FileWriter {

	private static final int MIN_BUFFER_SIZE = 1024 * 1; // KB
	private static final int MAX_BUFFER_SIZE = 1024 * 1024 * 64; // MB
	private static final int MIN_FILE_SIZE = 1024 * 1024 * 1; // MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 512; // MB
	private Timer timer = new Timer();
	private double benchScore;
	
	public double getBenchScore() {
		return Double.parseDouble(String.format("%.2f", benchScore));
	}
	
	public void streamWriteFixedSize(String filePrefix, String fileSuffix,
			int minIndex, int maxIndex, long fileSize, boolean clean)
			throws IOException {

		//System.out.println("Stream write benchmark with fixed file size");
		int currentBufferSize = MIN_BUFFER_SIZE;
		String fileName;
		int counter = 0;
		benchScore = 0;

		while (currentBufferSize <= MAX_BUFFER_SIZE
				&& counter <= maxIndex - minIndex) {
			fileName = filePrefix + counter + fileSuffix;
			writeWithBufferSize(fileName, currentBufferSize, fileSize, clean);
			currentBufferSize*=4;
			counter++;
		}

		benchScore /= (maxIndex - minIndex + 1);
		String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
		/*System.out.println("File write score on partition " + partition + ": "
				+ String.format("%.2f", benchScore) + " MB/sec");*/
	}

	public void streamWriteFixedBuffer(String filePrefix, String fileSuffix,
			int minIndex, int maxIndex, int bufferSize, boolean clean)
			throws IOException {

		int counter = 0;
		String fileName;
		benchScore = 0;
		
		//System.out.println("Stream write benchmark with fixed buffer size");
		int currentFileSize = MIN_FILE_SIZE;
		
		while (currentFileSize <= MAX_FILE_SIZE
				&& counter <= maxIndex - minIndex) {
			fileName = filePrefix + counter + fileSuffix;
			writeWithBufferSize(fileName, currentFileSize, bufferSize, clean);
			currentFileSize*=10;
			counter++;
			// update fileSize instead of bufferSize
		}

		benchScore /= (maxIndex - minIndex + 1);
		String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
		/*System.out.println("File write score on partition " + partition + ": "
				+ String.format("%.2f", benchScore) + " MB/sec");*/
	}

	private void writeWithBufferSize(String fileName, int myBufferSize,
			long fileSize, boolean clean) throws IOException {

		File folderPath = new File(fileName.substring(0,
				fileName.lastIndexOf(File.separator)));

		if (!folderPath.isDirectory())
			folderPath.mkdirs();

		final File file = new File(fileName);
		final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file), myBufferSize);

		byte[] buffer = new byte[myBufferSize];
		int i = 0;
		long toWrite = fileSize / myBufferSize;
		Random rand = new Random();

		timer.start();
		while (i < toWrite) {
			rand.nextBytes(buffer);

			outputStream.write(buffer);
			i++;
		}		
		printStats(fileName, fileSize, myBufferSize);

		outputStream.close();
		if(clean)
			file.deleteOnExit();
	}

	private void printStats(String fileName, long totalBytes, int myBufferSize) {
		NumberFormat nf = new DecimalFormat("#.00");
		final long time = timer.stop();
		double seconds = time/Math.pow(10, 9);
		double megabytes = totalBytes / Math.pow(1024, 2); 
		double rate = megabytes/seconds; 
		/*System.out.println("Done writing " + totalBytes + " bytes to file: "
				+ fileName + " in " + nf.format(seconds) + " ms ("
				+ nf.format(rate) + "MB/sec)" + " with a buffer size of "
				+ myBufferSize / 1024 + " kB");*/
		benchScore += rate;
	}
}
