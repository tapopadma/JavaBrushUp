package learn.thread;

public class PetersonAlgo {
	
	private static boolean [] readyToEnter = {false, false};
	private static int turn = 0;
	private static int counter = 0;//shared variable
	private static int MX = 1000000000;
	
	private static void threadMessage(String message) {
		System.out.println(Thread.currentThread().getName() + " : " + message);
	}
	
	private static class PetersonMethod implements Runnable{
		// This helps both the thread run uninterruptibly and 
		// maintain consistency of the shared variable

		private int threadNo;
		
		public PetersonMethod(int threadNo) {
			this.threadNo = threadNo;
		}

		@Override
		public void run() {
			lock(threadNo);
			enterCriticalSection();
			unlock(threadNo);
		}
		
		private void lock(int self) {
			threadMessage("setting readyToEnter to True");
			readyToEnter[self] = true;
			threadMessage("setting turn to other thread");
			turn = 1 - self;
			threadMessage("waiting...");
			while(readyToEnter[1 - self] && turn == 1 - self);
		}
		
		private void enterCriticalSection() {
			threadMessage("entering critcal section");
			for(int i=0;i<MX;++i)
				++counter;
			threadMessage("finished critical section");
		}
		
		private void unlock(int self) {
			threadMessage("setting readyToEnter to False");
			readyToEnter[self] = false;
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread(new PetersonMethod(0));
		Thread t1 = new Thread(new PetersonMethod(1));
		t0.start();
		t1.start();
		t0.join();t1.join();
		System.out.println("Final value of counter: " + counter);
	}

}
