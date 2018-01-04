import java.util.LinkedList;

class Big {
	private int[] arr;
	
	public Big(int size) {
		this.arr = new int[size];
	}
	
	public int access(int i) {
		return arr[i];
	}
	
}


public class GCTest {

	public static void main(String[] args) {
		LinkedList<Big> l = new LinkedList<Big>();
		int size = 65536;
		int iteration = 1;
		while (true) {
			System.out.println("Iteration: " + iteration);
			for (int i=0; i<10000; i++) {
				l.add(new Big(size));
			}
			while (!l.isEmpty()) {
				l.removeFirst();
			}
			iteration++;
		}

	}

}
