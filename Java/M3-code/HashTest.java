import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class HashTest {

    static class Point implements Comparable<Object> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object obj) {
            if (this == obj)
                return 0;
            Point other = (Point) obj;
            if (x != other.x)
                return (x - other.x);
            if (y != other.y)
                return (y - other.y);
            return 0;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }

    public static void benchmark(Collection<Point> s, String name, int tests) {
        System.out.format("%12s    ", name);
        Random r = new Random(12345);
        int range = 100000;
        long start = System.nanoTime();

        for (int i = 0; i < tests; i++) {
            s.add(new Point(r.nextInt(range), r.nextInt(range)));
        }

        long stop1 = System.nanoTime();
        System.out.format("%4.3f     ", (stop1 - start) / 1000000000.0);
        
        for (int i = 0; i < tests; i++) {
            s.contains(new Point(r.nextInt(range), r.nextInt(range)));
        }

        long stop2 = System.nanoTime();
        System.out.format("%4.3f%n", (stop2 - stop1) / 1000000000.0);
    }

    public static void main(String[] args) {
        Collection<Point> linkedList = new LinkedList<Point>();
        Collection<Point> arrayList = new ArrayList<Point>();
        Collection<Point> treeSet = new TreeSet<Point>();
        Collection<Point> hashSet = new HashSet<Point>();

        int tests = 100000;

        System.out.format("Class         Creation   Lookup%n");
        benchmark(hashSet,    "HashSet   ", tests);
        benchmark(treeSet,    "TreeSet   ", tests);
        benchmark(arrayList,  "ArrayList ", tests);
        benchmark(linkedList, "LinkedList", tests);
    }
}
