//  NOTE: this version of point is immutable!
public class Point implements Comparable<Point> {
	private final double x;
	private final double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		/*
		if (this.getX() < o.getX()) {
			return -1;
		} else if (this.getX() > o.getX()) {
			return 1;
		}
		if (this.getY() < o.getY()) {
			return -1;
		} else if (this.getY() > o.getY()) {
			return 1;
		} */
		return 0;
	}
	
}
