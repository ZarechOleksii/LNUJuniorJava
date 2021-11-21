
public class Main {

	public static void main(String[] args) {
		ThreadOneFrame r = new ThreadOneFrame();
		ThreadTwoFrame r2 = new ThreadTwoFrame();
		ThreadThreeFrame r3 = new ThreadThreeFrame();
		r.other = r3.toMove;
		Thread t = new Thread(r);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		t.start();
		t2.start();
		t3.start();
	}

}
