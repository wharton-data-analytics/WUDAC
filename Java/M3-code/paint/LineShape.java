package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Point;


/** A line that may be drawn on the canvas. */
public class LineShape implements Shape {
	
	private Color color;
	private Stroke stroke;
	private Point start;
	private Point end;
	
	 /** Construct a line, given its color, line thickness, and the two 
		  end points.

		  The constructor assumes that all parameters are nonnull.
	 */
	public LineShape(Color c, Stroke s, Point s0, Point e0) {
		color  = c;
		stroke = s;
		start  = s0;
		end    = e0;
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
		gc.drawLine(start.x, start.y, end.x, end.y);
	}
	

}
