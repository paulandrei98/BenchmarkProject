package Timing;

public class Timer implements ITimer{
	private static long totalTime = 0;
	private long startTime = 0;
	private long stopTime = 0;
	
	private boolean resume = false;
	private boolean start = false;
	private boolean pause = false;

	@Override
	public void start() {
		resume = true;
		start = true;
		totalTime = 0;
		
		startTime=System.nanoTime();
	}

	@Override
	public long stop() {
		stopTime = System.nanoTime();
		totalTime = totalTime+stopTime-startTime;
		
		resume = false;
		pause = false;
		start = false;
		
		long aux = totalTime;
		totalTime = 0;
		
		return aux;
	}

	@Override
	public long pause() {
		stopTime = System.nanoTime();
		totalTime = totalTime+stopTime-startTime;
		
		resume = false;
		pause = true;
		
		long aux = stopTime-startTime;
		
		return aux;
	}

	@Override
	public void resume() {
		startTime=System.nanoTime();
		
		resume=true;
		pause=false;
		
	}
}
