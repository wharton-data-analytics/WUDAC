import java.util.HashMap;
import java.util.Map;

public class HashExample {

	public static void main(String[] args) {
		Map<Point,String> m = new HashMap<Point,String>();
		m.put(new Point(1,2), "House");
		System.out.println(m.containsKey(new Point(1,2)));
	}

}
