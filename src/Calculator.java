import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

/**
 * This is a panel that help customers to
 * deal with the number. It can calculate 
 * the total price of the order, it can 
 * calculate the calories as well.
 * 
 * @author Yiwen Yu
 * @version 1.0
 */

public class Calculator extends JPanel{
	String meals [] = {null, "Chicken wrap", "Steak", "BLT sandwich", 
			"Spaghetti", "Ham and egg sandwich", "Hamburger", 
			"Cream waffle", "Orange juice", "Coke",
			"Milk", "Fries", "Vegetable salad"};
	
	JComboBox mealBox;
	JComboBox otherBox;
	JButton priceCal;
	JButton caloriesCal;
	JLabel selectMeal;
	JLabel selectOther;
	int mealPrice, otherPrice, mealCalories, otherCalories; 
	String mealName, otherName;
	JLabel result;
	int sum;
	Font font = new Font("Arial", Font.PLAIN, 26);		
	
	Connection connection = null;
	
	void connectDB() throws ClassNotFoundException, SQLException{
		//Connect to the database
		try{
			Class.forName("org.sqlite.JDBC"); 
			connection = DriverManager.getConnection("jdbc:sqlite:menu.db");
			
			}catch(SQLException e){
			System.err.println(e);
			} 
		}
	
	Calculator(){
		setLayout(new GridLayout(4, 2));
		result = new JLabel();
		result.setFont(font);
		mealBox = new JComboBox(meals);
		mealBox.setFont(font);
		otherBox = new JComboBox(meals);
		otherBox.setFont(font);
		selectMeal = new JLabel("Select what you like");
		selectMeal.setFont(font);
		selectOther = new JLabel("Any addon?");
		selectOther.setFont(font);
		priceCal = new JButton("Price calculator");
		priceCal.setFont(font);
		caloriesCal = new JButton("Calories calculator");
		caloriesCal.setFont(font);
		
		//Get selected food
		otherBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				otherName = meals [otherBox.getSelectedIndex()];
			}
		});
		
		//Get selected food
		mealBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				mealName = meals [mealBox.getSelectedIndex()];
			}
		});
		
		//Calculate price
		priceCal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					connectDB();
		
					if(mealName == null){
						mealPrice = 0;
					}else{
						Statement statement = connection.createStatement();
						statement.setQueryTimeout(10);
						ResultSet rs1 = statement.executeQuery("SELECT Price FROM food WHERE name = '"+mealName+"'");
						String temp = rs1.getString("Price");
						mealPrice = Integer.parseInt(temp);
						statement.close();
					}
					
					if(otherName == null){
						otherPrice = 0;
					}else{
						Statement statement = connection.createStatement();
						statement.setQueryTimeout(10);
						ResultSet rs2 = statement.executeQuery("SELECT Price FROM food WHERE name = '"+otherName+"'");
						String temp = rs2.getString("Price");
						otherPrice = Integer.parseInt(temp);
						statement.close();
					}
					//Display result
					sum = mealPrice + otherPrice;
					result.setText("Total Price: $ " + sum);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Calculate calories
		caloriesCal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					connectDB();
					
					if(mealName == null){
						mealCalories = 0;
					}else{
						Statement statement = connection.createStatement();
						statement.setQueryTimeout(10);
						ResultSet rs3 = statement.executeQuery("SELECT Calories FROM food WHERE name = '"+mealName+"'");
						String temp = rs3.getString("Calories");
						mealCalories = Integer.parseInt(temp);
						statement.close();
					}
					
					
					if(otherName == null){
						otherCalories = 0;
					}else{
						Statement statement = connection.createStatement();
						statement.setQueryTimeout(10);
						ResultSet rs4 = statement.executeQuery("SELECT Calories FROM food WHERE name = '"+otherName+"'");
						String temp = rs4.getString("Calories");
						otherCalories = Integer.parseInt(temp);
						statement.close();
					}
					//Display result
					sum = mealCalories + otherCalories;
					result.setText("Total Calories: " + sum);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Add components to the panel
		add(mealBox);
		add(otherBox);
		add(selectMeal);
		add(selectOther);
		add(caloriesCal);
		add(priceCal);
		add(result);
	}
}
