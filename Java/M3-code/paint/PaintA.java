package paint;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

// Basic User interface and state of the application
// Only the quit button works so far.

/**
 * The main class of the Java Paint application.
 * 
 * This class stores the state of the application, and creates the user
 * interface.
 */
public class PaintA {

	/** The list of shapes that will be drawn on the canvas. */
	private List<Shape> shapes = new LinkedList<Shape>();

	/** Preview shape for drag and drop */
	private Shape preview = null;

	/** Area of the screen used for drawing */
	private Canvas canvas = new Canvas();

	/**
	 * A Canvas is a section of the screen that can be drawn on.
	 * 
	 * Just like in the OCaml Paint assignment, we draw with a repainting
	 * function that uses some local state to determine what to draw. In Swing,
	 * the drawing method of a widget is called "paintComponent"
	 * 
	 * The canvas is an *inner* class so that it can access the private 'shapes'
	 * and 'preview' fields.
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

	/*
	 * A helper method for creating radio buttons (i.e. line and point.) Each
	 * one needs to be created, added to the button group, added to the panel
	 * and have an action listener installed. To avoid code duplication, we do
	 * that all here.
	 */
	private JRadioButton makeShapeButton(ButtonGroup group, JPanel toolbar, final String name) {
		JRadioButton b = new JRadioButton(name);
		group.add(b);
		toolbar.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// does nothing for now
			}
		});
		return b;
	}

	private JPanel createToolbar() {

		JPanel toolbar = new JPanel();

		// Create the group of buttons that select the mode
		ButtonGroup group = new ButtonGroup();

		// create buttons for points and lines, and add them to the list
		JRadioButton point = makeShapeButton(group, toolbar, "Point");
		makeShapeButton(group, toolbar, "Line");
		// add more shapes here

		// start by selecting the buttons for points
		point.doClick();

		// the checkbox for thin/thick lines
		JCheckBox thick = new JCheckBox("Thick Lines");
		toolbar.add(thick);

		// exiting the program
		JButton quit = new JButton("Quit");
		toolbar.add(quit);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return toolbar;
	}

	public PaintA() {
		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		frame.add(canvas, BorderLayout.CENTER);
		frame.add(createToolbar(), BorderLayout.PAGE_END);

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PaintA();
			}
		});
	}

}
