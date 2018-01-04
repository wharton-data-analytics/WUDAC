import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

// By default JComponents are supposed to declare a  
// serialVersionUID field (useful for larger projects),
// we suppress the warning since we don't need it.
@SuppressWarnings("serial")
public class DrawingCanvas extends JComponent {
    
    /* This paint function draws a fractal tree -- Google "L-systems" for 
     * more explanation / inspiration.
     * It is the same as the fractal drawing from the OCaml lecture. */
    private static void fractal(Graphics gc, int x, int y, 
            double angle, double len) {
        if (len > 1) {
            double af = (angle * Math.PI) / 180.0;
            int nx = x + (int)(len * Math.cos(af));
            int ny = y + (int)(len * Math.sin(af));
            gc.drawLine(x, y, nx, ny);
            fractal(gc, nx, ny, angle + 20, len - 8);
            fractal(gc, nx, ny, angle - 10, len - 8);
        }
    }
    
    @Override
    public void paintComponent(Graphics gc) {
    	// we're overriding the method, but we want the 
    	// old version to run first
        super.paintComponent(gc);
        
        // set the pen color to green
        gc.setColor(Color.GREEN);
        
        // draw a fractal tree
        fractal (gc, 200, 450, 270, 80);
    }
    
    // get the size of the drawing panel
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }
    
}
