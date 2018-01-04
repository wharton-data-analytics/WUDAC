import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		Set<Point> s = new TreeSet<Point>();
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(1,1);
		// s.add(p1);
		// s.add(p2);
		
		if (s.contains(new Point(0,0))) {
			System.out.println("s contains (0,0)");
		} else {
			System.out.println("s does not contain (0,0)");
		}
		
		
	}
}
