import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;

/**
 * This is the main Structure of the Menu, it use 3 tabs 
 * to switch content that displayed. Each tab has a tooltiptext
 * to illustrate itself.
 * 
 * @author Yiwen Yu
 * @version 1.0
 */

class menu{
	JPanel CR; 
	JPanel Meal;
	JPanel Calculator;
	
	menu(){
		//Create an new JFrame container
		JFrame jfrm = new JFrame("Menu");
		
		//Set the size of JFrame
		jfrm.setSize(600, 600);
		
		//Terminate the program when exits
		jfrm.setDefaultCloseOperation(jfrm.EXIT_ON_CLOSE);
		
		Font font = new Font("Arial", Font.PLAIN, 22);		
		
		//Create JTabbedPane
		JTabbedPane jtp = new JTabbedPane();
		CR cr = new CR();
		Meal meal = new Meal();
		Calculator cal = new Calculator();
		jtp.setFont(font);
		jtp.add("Chef recommend", cr);
		jtp.add("Meal", meal);
		jtp.add("Calculator", cal);
		
		//Add ToolTipText
		jtp.setToolTipTextAt(0, "Enjoy our most recommendation!");
		jtp.setToolTipTextAt(1, "See what we have!");
		jtp.setToolTipTextAt(2, "Doing the math!");
		
		//Add JTabbedPane to the JFrame
		jfrm.add(jtp);
		
		jfrm.setVisible(true);
		
	}

	public static void main(String args[]) {  
	    // Create the frame on the event dispatching thread.  
	    SwingUtilities.invokeLater(new Runnable() {  
	      public void run() {  
	        new menu();  
	      }  
	    }); 
	  }  
	}





