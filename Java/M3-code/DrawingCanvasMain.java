import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawingCanvasMain implements Runnable {

    public static void main(String[] args) {
        // The proper way to create a top-level window:
        //   - make a class that implements Runnable
        //   - the 'run' method creates the actual window
        //   - SwingUtilities.invokeLater calls the 'run'
        //     method after initializing the program state. 
        SwingUtilities.invokeLater(new DrawingCanvasMain());
    }

    @Override
    public void run() {             
        // create the top-level window
        JFrame frame = new JFrame("Tree");
        
        DrawingCanvas tree = new DrawingCanvas();
        
        // add the canvas to the frame's content pane
        frame.getContentPane().add(tree);
        
        // pack sets the size of the frame automatically
        // based on the sub-components' preferred sizes
        frame.pack();
        
        // tell the frame to display itself
        frame.setVisible(true);
        
        // make sure to end the program when the window is 
        // closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
