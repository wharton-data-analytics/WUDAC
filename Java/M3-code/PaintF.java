import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

// Refactor to use objects instead of Enums
// for the mouse listener

/**
 * The main class of the Java Paint application.
 * 
 * This class stores the state of the application, and creates the user
 * interface.
 */
public class PaintF {

	/** Area of the screen used for drawing */
	private Canvas canvas;

	/** Current drawing color */
	private Color color = Color.BLACK;

	/** Stroke for drawing shapes with thin lines */
	public final static Stroke thinStroke = new BasicStroke(1);

	/** Stroke for drawing shapes with thick lines */
	public final static Stroke thickStroke = new BasicStroke(3);

	/** Current drawing thickness */
	private Stroke stroke = thickStroke;

	interface Mode extends MouseListener, MouseMotionListener {
	}

	class PointMode extends MouseAdapter implements Mode {
		public void mouseReleased(MouseEvent e) {
			Point p = e.getPoint();
			shapes.add(new PointShape(color, stroke, p));
		}

	}

	class LineStartMode extends MouseAdapter implements Mode {
		public void mousePressed(MouseEvent e) {
			mode = new LineEndMode(e.getPoint());
		}

	}

	class LineEndMode extends MouseAdapter implements Mode {
		Point modePoint;

		LineEndMode(Point p) {
			modePoint = p;
		}

		public void mouseDragged(MouseEvent arg0) {
			Point p = arg0.getPoint();
			preview = new LineShape(color, stroke, modePoint, p);
		}

		public void mouseReleased(MouseEvent arg0) {
			mode = new LineStartMode();
			Point p = arg0.getPoint();
			shapes.add(new LineShape(color, stroke, modePoint, p));
			modePoint = null;
			preview = null;

		}

	}

	private Mode mode = null;

	/** The list of shapes that will be drawn on the canvas. */
	private final List<Shape> shapes = new LinkedList<Shape>();

	/** an optional shape for preview mode */
	private Shape preview;

	/**
	 * A Canvas is a section of the screen that can be drawn on.
	 * 
	 * Just like in the OCaml Paint assignment, we draw with a repainting
	 * function that uses some local state to determine what to draw. In Swing,
	 * the drawing method of a widget is called "paintComponent"
	 * 
	 * The canvas is an *inner* class so that it can access the private
	 * 'actions' and 'preview' fields of the Paint.
	 */
	@SuppressWarnings("serial")
	private class Canvas extends JPanel {

		/**
		 * Display the canvas on the screen using the Graphics Context.
		 * 
		 * This method overrides the analogous method in its superclass.
		 * Therefore the first action it does is to call the superclass version
		 * of the method. Then it goes through each shape and invokes its draw
		 * method.
		 */
		@Override
		public void paintComponent(Graphics gc) {
			super.paintComponent(gc);
			if (shapes != null) {
				for (Shape s : shapes) {
					s.draw(gc);
				}
			}
			if (preview != null) {
				preview.draw(gc);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(600, 400);
		}

		public Canvas() {
			super();
			setBackground(Color.WHITE);
		}

	}

	private class Mouse extends MouseAdapter {

		/**
		 * Code to execute when the button is pressed while the mouse is in the
		 * canvas.
		 *
		 * This method is in the Paint class so that it can access and update
		 * the state of the paint application.
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {

			mode.mousePressed(arg0);
			canvas.repaint();
		}

		/**
		 * Code to execute when the button is pressed while the mouse is moved
		 * with the button down in the canvas.
		 */
		public void mouseDragged(MouseEvent arg0) {
			mode.mouseDragged(arg0);
			canvas.repaint();
		}

		/**
		 * Code to execute when the button is released while the mouse is in the
		 * canvas.
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
			mode.mouseReleased(arg0);
			canvas.repaint();
		}
	}

	// add an action listener that specifies the code to run when
	// the button is selected
	private JRadioButton makeShapeButton(ButtonGroup group, JPanel modeToolbar, String name, final Mode buttonMode) {
		JRadioButton b = new JRadioButton(name);
		group.add(b);
		modeToolbar.add(b);
		b.addActionListener(event -> {
			mode = buttonMode;
			preview = null; 
		});
		return b;
	}

	private JPanel createModeToolbar() {

		JPanel modeToolbar = new JPanel();

		// Create the group of buttons that select the mode
		ButtonGroup group = new ButtonGroup();

		// create buttons for points and lines, and add them to the list
		JRadioButton point = makeShapeButton(group, modeToolbar, "Point", new PointMode());
		makeShapeButton(group, modeToolbar, "Line", new LineStartMode());
		// add more shapes here

		// start by selecting the buttons for points
		point.doClick();

		// the checkbox for thin/thick lines
		JCheckBox thick = new JCheckBox("Thick Lines");
		modeToolbar.add(thick);
		thick.addItemListener(e -> {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					stroke = thickStroke;
				} else {
					stroke = thinStroke;
				}
			}
		);
		// start with thick lines
		thick.doClick();

		// exiting the program
		JButton quit = new JButton("Quit");
		modeToolbar.add(quit);
		quit.addActionListener(e -> System.exit(0));
		return modeToolbar;
	}

	public PaintF() {

		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		canvas = this.new Canvas();

		Mouse mouseListener = new Mouse();
		canvas.addMouseMotionListener(mouseListener); // dragged events
		canvas.addMouseListener(mouseListener); // press/release events

		frame.add(canvas, BorderLayout.CENTER);
		frame.add(createModeToolbar(), BorderLayout.PAGE_END);

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new PaintF());
	}

}
