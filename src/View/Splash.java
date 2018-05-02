package View;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class Splash extends JWindow {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Splash(Image image, int millis){
        ImageIcon icon=new ImageIcon(image);
        add(new JLabel(icon));
        pack();
        setLocationRelativeTo(null);
        ActionListener hideAction = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        };
        Timer timer = new Timer(millis, hideAction);
        setVisible(true);
        timer.start();
    }
}