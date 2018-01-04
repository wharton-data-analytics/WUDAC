import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Streams {
	
	 public static void benchmark(int tests) {
	        System.out.format("Sequential Stream - ");
	        Random r = new Random(12345);
	        List<Integer> s = new ArrayList<Integer>(tests);

	        for (int i = 0; i < tests; i++) {
	            s.add(r.nextInt(tests));
	        }
	        
	        long start = System.nanoTime();
	        for (int i = 0; i < tests; i++) {
	            s.stream()
	            .map(x -> x * x)
	            .reduce(0, Integer::sum);
	        }

	        long stop1 = System.nanoTime();
	        System.out.format("%4.3f%n", (stop1 - start) / 1000000000.0);

	        System.out.format("Parallel Stream - ");
	        for (int i = 0; i < tests; i++) {
	            s.parallelStream()
	            	.map(x -> x * x)
	            .reduce(0, Integer::sum);
	        }

	        long stop2 = System.nanoTime();
	        System.out.format("%4.3f%n", (stop2 - stop1) / 1000000000.0);

	    }

	public static void main(String[] args) {
		
		int tests = 100000;

        benchmark(tests);
        
	}

}
