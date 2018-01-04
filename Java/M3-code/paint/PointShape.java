package paint;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Point;


/** A point that may be drawn on the canvas. */
public class PointShape implements Shape {
	private Point point;  
	private Color color;
	private Stroke stroke;

	/** Construct a point given its color, stroke and location.

     The constructor assumes that all parameters are nonnull.
	 */
	public PointShape (Color c, Stroke s, Point p) {
		color  = c;
		stroke = s;
		point  = p;
	}

	/* The dynamic class of gc is actually as subclass of 
	 * Graphics called Graphics2D that supports more functionality. However,
	 * due to backwards compatibility, the argument to paintComponent has 
	 * the static type of "Graphics". Therefore, we must do a dynamic 
	 * cast before we can use some drawing routines.
	 */
	public void draw(Graphics gc0) {
		Graphics2D gc = (Graphics2D)gc0;
		gc.setColor(color);
		gc.setStroke(stroke);
		gc.drawLine(point.x, point.y, point.x, point.y);

	}

}
