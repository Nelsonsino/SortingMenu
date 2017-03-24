import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the first panel to display the food
 * recommended by the chef, with pictures and 
 * price. Because of the unknown reason, after
 * select the radioButton, you have to click on
 * the windows one more time to display the 
 * content.
 * 
 * @author Yiwen Yu
 * @version 1.0
 */

public class Meal extends JPanel{
	JRadioButton sort1;
	JRadioButton sort2;
	JScrollPane jsp;
	String orders [] = {"From low to high", "From high to low"}; 
	ButtonGroup group;
	JTable table;
	Font font = new Font("Arial", Font.PLAIN, 26);		
	Font font2 = new Font("Arial", Font.BOLD, 17);		
	
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
	
	//Create table builder
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException{
		//Get data
		ResultSetMetaData metaData = rs.getMetaData();
		
		//Create title of each column
		Vector<String> cNames = new Vector<String>();
		int colCount = metaData.getColumnCount();
		
		for(int i = 1; i <= colCount; i++){
			cNames.add(metaData.getColumnName(i));
		}
		
		//Create the context of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		while(rs.next()){
			Vector<Object> vector = new Vector<Object>();
			for(int j = 1; j <= colCount; j++){
				vector.add(rs.getObject(j));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, cNames);
	}
	
	
	Meal(){
		setOpaque(true); 
		setLayout(new BorderLayout());
		
		sort1 = new JRadioButton("Sorted by price");
		sort1.setPreferredSize(new Dimension(250,30));
		sort1.setFont(font);
		sort2 = new JRadioButton("Sorted by calories");
		sort2.setPreferredSize(new Dimension(250,30));
		sort2.setFont(font);
		
		//Sort the result by alphabet
		try {
			connectDB();
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(10);
			ResultSet rs = statement.executeQuery("SELECT * FROM food ORDER BY Name");
			table = new JTable(buildTableModel(rs));
			table.setFont(font2);
			jsp = new JScrollPane(table);
			add(jsp, BorderLayout.SOUTH);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			//Sort the result by price
		sort1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				try {
					remove(jsp);
					connectDB();
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(10);

					ResultSet rs = statement.executeQuery("SELECT * FROM food ORDER BY price");
					table = new JTable(buildTableModel(rs));
					table.setFont(font2);
					jsp = new JScrollPane(table);
					add(jsp, BorderLayout.SOUTH);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Sort the result by calories
		sort2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				try {
					remove(jsp);
					connectDB();
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(10);

					ResultSet rs = statement.executeQuery("SELECT * FROM food ORDER BY calories");
					table = new JTable(buildTableModel(rs));
					table.setFont(font2);
					jsp = new JScrollPane(table);
					add(jsp, BorderLayout.SOUTH);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Add radioButtons into the group
		group = new ButtonGroup();
		group.add(sort1);
		group.add(sort2);
		
		//Add buttons to the panel
		add(sort1, BorderLayout.WEST);
		add(sort2, BorderLayout.EAST);
		
	}
}