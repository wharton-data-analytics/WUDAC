import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This file demonstrates various Layout managers that Swing uses to position
 * JComponents. The most important ones are FlowLayout (the default for JPanel),
 * GridLayout, and BorderLayout.
 * 
 * See http://docs.oracle.com/javase/tutorial/uiswing/layout/index.html
 * for more information
 */
public class LayoutDemo implements Runnable {

    public void run() {
        JFrame frame = new JFrame("Layout Example");
        frame.setContentPane(borderLayoutPanel());
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LayoutDemo());
    }

    /** Create a panel that demonstrates the use of the 
     * FlowLayout layout manager.
     * 
     * As this panel is resized, notice what happens to the 
     * sizes and positions of the five buttons displayed in 
     * the window.
     */
    public JPanel flowLayoutPanel() {
        JPanel panel = new JPanel();

        JButton b1 = new JButton("one");
        JButton b2 = new JButton("two");
        JButton b3 = new JButton("three");
        JButton b4 = new JButton("four");
        JButton b5 = new JButton("five");

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        return panel;

    }
    
    /** Create a panel that demonstrates the use of the 
     * GridLayout layout manager.
     */
    public JPanel gridLayoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3));

        JButton b1 = new JButton("one");
        JButton b2 = new JButton("two"); 
        JButton b3 = new JButton("three");
        JButton b4 = new JButton("four");
        JButton b5 = new JButton("five");

        panel.add(b1);
        JPanel innerPanel = new JPanel();
        innerPanel.add(b2);
        panel.add(innerPanel);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        
        return panel;
    }

    /** Create a panel that demonstrates the use of the 
     * BorderLayout layout manager.
     */
    public JPanel borderLayoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton b1 = new JButton("one");
        JButton b2 = new JButton("two");
        JButton b3 = new JButton("three");
        JButton b4 = new JButton("four");
        JButton b5 = new JButton("five");

        JPanel inner = new JPanel();
        inner.add(b1);
        panel.add(inner, BorderLayout.CENTER);
        panel.add(b2, BorderLayout.PAGE_START);  // top
        panel.add(b3, BorderLayout.PAGE_END);    // bottom
        panel.add(b4, BorderLayout.LINE_START);  // left 
        panel.add(b5, BorderLayout.LINE_END);    // right

        return panel;
    }


}
