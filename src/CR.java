import java.awt.*;
import javax.swing.*;

/**
 * This Panel displays all the food the restaurant
 * offers, including meals, drinks, and appetizer.
 * It can change the order of display, and the default
 * order is alphabet.
 * 
 * @author Yiwen Yu
 * @version 1.0
 */

public class CR extends JPanel {
	CR(){
		setOpaque(true); 
		setLayout(new GridLayout(2,2));
		
		Font font = new Font("Arial", Font.ITALIC, 20);		
		
		//Add hamburger info
		ImageIcon hamburger = new ImageIcon("hamburger.jpg");
		JLabel label1 = new JLabel(hamburger);
		label1.setText("Hamburger $8");
		label1.setFont(font);
		label1.setVerticalTextPosition(JLabel.BOTTOM);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		
		//Add cream waffle info
		ImageIcon creamwaffle = new ImageIcon("creamwaffle.jpg");
		JLabel label2 = new JLabel(creamwaffle);
		label2.setText("Cream waffle $8");
		label2.setFont(font);
		label2.setVerticalTextPosition(JLabel.BOTTOM);
		label2.setHorizontalTextPosition(JLabel.CENTER);
		
		//Add fries info
		ImageIcon fries = new ImageIcon("fries.jpg");
		JLabel label3 = new JLabel(fries);
		label3.setText("Fries $6");
		label3.setFont(font);
		label3.setVerticalTextPosition(JLabel.BOTTOM);
		label3.setHorizontalTextPosition(JLabel.CENTER);
				
		//Add steak info
		ImageIcon steak = new ImageIcon("steak.jpg");
		JLabel label4 = new JLabel(steak);
		label4.setText("Steak $16");
		label4.setFont(font);
		label4.setVerticalTextPosition(JLabel.BOTTOM);
		label4.setHorizontalTextPosition(JLabel.CENTER);
		
		//Add components to the panel
		add(label1);
		add(label2);
		add(label3);
		add(label4);
	}
}
