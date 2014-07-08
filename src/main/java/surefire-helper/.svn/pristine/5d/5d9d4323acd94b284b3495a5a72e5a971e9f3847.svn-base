package edu.ucsd.cs;

public class DaikonThread extends Thread {
	
	public DaikonThread(Runnable target) {
		super(target);
		this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				System.out.println("Daikon thread exits.");
			}
			
		});
	}

	public static final ThreadLocal<Boolean> daikonThread = 
			new InheritableThreadLocal<Boolean>() {
		@Override protected synchronized Boolean initialValue(){
			boolean value = (Thread.currentThread() instanceof DaikonThread);
			return value;
		}
		@Override protected synchronized Boolean childValue(Boolean parentValue) {
			boolean value = (Thread.currentThread() instanceof DaikonThread || parentValue);
			return value;
		}
	};

}
