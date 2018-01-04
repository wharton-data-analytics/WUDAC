package ioexamples;

import java.io.*;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class Image extends JComponent {
	
	// size of the image
    private final static int WIDTH  = 512;
    private final static int HEIGHT = 512;
    
    // 2D array of b/w pixels values
    private final int[][] data = new int[WIDTH][HEIGHT];
   
    public void copy() throws IOException {
    		OutputStream out = 
    				new FileOutputStream("mandrill-copy.pgm");
    		PrintStream p = new PrintStream(out);
    		p.println("P5");
    		p.println("512 512");
    		p.println("255");
    		for (int i=0; i<HEIGHT; i++) {
    			for (int j=0; j<WIDTH; j++) {
    				p.write(255 - data[j][i]);
    			}
    		}
    		p.close();
    }
    // construct an image by reading a sequence of bytes from 
    // a file into the data array
    public Image() throws IOException {
    		InputStream fin = 
    				new FileInputStream("mandrill-copy.pgm");
    		fin.skip(15);
    	
    		for (int i=0; i<HEIGHT; i++) {
    			for (int j=0; j<WIDTH; j++) {
    				int v = fin.read();
    				data[j][i] = v;
    			}
    		}
    		
    		fin.close();
    }   

    // Some code to display the image once it has been created.
    public static void main(String[] args) {
    	  try {
        Image i = new Image();
        i.copy();
        JFrame f = new JFrame("image");
        f.setContentPane(i);
        f.setSize(WIDTH,HEIGHT);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  } catch (FileNotFoundException e) {
    		  System.out.println("Stephanie make sure the file is there");
    	  } catch (IOException e) {
    		  System.out.println("Something happened " + 
    	       e.getMessage());
    	  }
    }

    // Display the stored image.  Of course, the Java Swing libraries 
    // provide much better functionality to work with image data, including
    // classes for directly reading images from disk and displaying them 
    // to the screen. This demo is to give you an idea of what those classes
    // might be doing. This is *not* the way to display images in Swing.
    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {
                gc.setColor(new Color(data[i][j], data[i][j], data[i][j]));
                gc.drawLine(i, j, i, j);
            }
        }
    }
}
