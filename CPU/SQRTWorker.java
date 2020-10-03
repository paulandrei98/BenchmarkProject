package CPU;


public class SQRTWorker implements Runnable {

	private int start;
	private int stop;

	public SQRTWorker(int start, int stop) {
		this.start = start;
		this.stop = stop;
	}

	// return the square root of c, computed using Newton's method
	private double sqrt(double c) {
		if (c < 0)
			return Double.NaN;
		double EPS = 1E-15;
		double t = c;
		while (Math.abs(t - c / t) > EPS * t)
			t = (c / t + t) / 2.0;
		return t;
	}

	@Override
	public void run() {
		for (int i = start; i <= stop; i++)
			this.sqrt(i);
	}
}
