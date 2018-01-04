import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class ExceptionExamples {
	
	public static void iteratorExample() {
		List<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(4);
		
		int sum = 0;
		int cnt = 0;
		for(int i: l) {
			sum = sum + i;
			cnt = cnt + 1;
		}
		System.out.println("sum = " + sum);
		System.out.println("cnt = " + cnt);
	}
	
	
	public static void foo() {
		bar();
		System.out.println("here in foo");
	}
	
	public static void bar() {
		// try {
		baz(); 
		// } catch (Exception e) {
		//  System.out.println("caught!");
		// }
		System.out.println("here in bar");
	}
	
	public static void baz() {
		throw new RuntimeException("baz threw this!");
		// System.out.println("here in baz");
	}
	
	public static void main(String[] args) {
		// iteratorExample();
		foo();
	}
	
}
