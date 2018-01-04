/* A Swing version of the Light switch GUI program  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class LightBulb extends JComponent {
    
	private boolean isOn = false;
	
	public void flip() {
		isOn = !isOn;
	}
	
    @Override
    public void paintComponent(Graphics gc) {
       // display the light bulb here
    		if (isOn) {
    			gc.setColor(Color.YELLOW);
    		} else {
    			gc.setColor(Color.BLACK);
    		}
    		gc.fillRect(0, 0, 100, 100);
    	
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100,100);
    }
    
}

class ButtonListener implements ActionListener {
	private LightBulb bulb;
	public ButtonListener (LightBulb b) {
		bulb = b;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		bulb.flip();
		bulb.repaint();
	}
	
}

public class OnOff implements Runnable {
  public void run() {
      JFrame frame = new JFrame("On/Off Switch");

      // Create a panel to store the two components
      // and make this panel the contentPane of the frame
      JPanel panel = new JPanel();    
      frame.getContentPane().add(panel);   

      LightBulb bulb = new LightBulb();
      panel.add(bulb);
      
      JButton button = new JButton("On/Off");
      panel.add(button);
      
      button.addActionListener(new ButtonListener(bulb));
    
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
  }

  public static void main(String[] args) {
      SwingUtilities.invokeLater(new OnOff()); 
  }
}
