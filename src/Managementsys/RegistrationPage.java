package Managementsys;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;


public class RegistrationPage extends JFrame implements ActionListener{
	
	
	 private static final long serialVersionUID = 1L;
	 private Connection con;
	 private JPanel contentPane;
	 private JLabel username, regid, contact_no, room_no, roomtype, arr_date, dep_date;
	 private JTextField t,t1,t2,t3,t4,t5;
	 private JButton Register_btn;
	 private JComboBox<String> cb;
	
	 RegistrationPage(){
		    contentPane = new JPanel();
	        contentPane.setLayout(null);
	        setBounds(100, 100, 670, 525);
	        setContentPane(contentPane);
	        //contentPane.setSize(500, 500);
	        
	        username = new JLabel("Name:");
	        username.setBounds(120, 80, 194, 36);
	        contentPane.add(username);
	        
	        regid = new JLabel("Registration ID");
	        regid.setBounds(120, 127, 194, 33);
	        contentPane.add(regid);
	        
	        contact_no = new JLabel("Contact No.");
	        contact_no.setBounds(120, 174, 194, 33);
	        contentPane.add(contact_no);
	        
	        room_no = new JLabel("Room No");
	        room_no.setBounds(120, 221, 194, 32);
	        contentPane.add(room_no);
	        
	        roomtype = new JLabel("Room Type");
	        roomtype.setBounds(120, 268, 194, 33);
	        contentPane.add(roomtype);
	        
	        arr_date = new JLabel("Arrival Date");
	        arr_date.setBounds(120, 315, 194, 33);
	        contentPane.add(arr_date);
	        
	        dep_date = new JLabel("Departure Date");
	        dep_date.setBounds(120, 362, 194, 33);
	        contentPane.add(dep_date);
	        
	        String[] roomTypes = {"Select", "Standard Room", "King Suite", "Double Bed"};
	        cb = new JComboBox<>(roomTypes);
	        cb.setBounds(217, 268, 194, 36);
	        contentPane.add(cb);
	        
	        // name field
	        t = new JTextField(); 
	        t.setBounds(217, 80, 194, 36);
	        contentPane.add(t);
	        
	        // registration id field
	        t1 = new JTextField(); 
	        t1.setBounds(217, 127, 194, 33);
	        contentPane.add(t1);
	        
	        // contact no. field
	        t2 = new JTextField(); 
	        t2.setBounds(217, 174, 194, 33);
	        contentPane.add(t2);
	        
	        // room no. field
	        t3 = new JTextField(); 
	        t3.setBounds(217, 221, 194, 33);
	        contentPane.add(t3);
	        
	        // arrival date field
	        t4 = new JTextField("YYYY-MM-DD"); 
	        t4.setBounds(217, 315, 194, 33);
	        contentPane.add(t4);
	        
	        // departure date field
	        t5 = new JTextField("YYYY-MM-DD"); 
	        t5.setBounds(217, 362, 194, 33);
	        contentPane.add(t5);
	        
	        
	        Register_btn = new JButton("Register");
	        Register_btn.setBounds(180, 409, 160, 33);
	        contentPane.add(Register_btn);
	        
	        
	        Register_btn.addActionListener(this);
	        
	        contentPane.setBackground(Color.LIGHT_GRAY);
	        setTitle("Registration Platform");
	        setResizable(false);
	        setVisible(true);

	 }
		 
		 
	 public void actionPerformed(ActionEvent e) {
			String url = "Jdbc:mysql://localhost:3306/acp_project";
			String username = "root";
			String password = "khixerimt89@";
			
			String arrivalDate = t4.getText();
			String departureDate = t5.getText();
			String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
			if (!arrivalDate.matches(regex) || !departureDate.matches(regex)) {
				JOptionPane.showMessageDialog(null, "Invalid arrival or departure date format. Please use YYYY-MM-DD.");
			    return;
			    }
			
			try {
				con = DriverManager.getConnection(url, username, password);
			    System.out.println("Connected to database");
				String qry_insert = "INSERT INTO HMS (name, regID, contactNo, roomNo,  roomType, arrivaldate, departuredate) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement statement = con.prepareStatement(qry_insert);
				
				java.util.Date arrivalUtilDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
			    java.sql.Date arrivalSqlDate = new java.sql.Date(arrivalUtilDate.getTime());
			    java.util.Date departureUtilDate = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
			    java.sql.Date departureSqlDate = new java.sql.Date(departureUtilDate.getTime());

				statement.setString(1, t.getText());
				statement.setString(2, t1.getText());
				statement.setString(3, t2.getText());
				statement.setString(4, t3.getText());
				statement.setString(5, (String) cb.getSelectedItem());
				statement.setDate(6, arrivalSqlDate);
				statement.setDate(7, departureSqlDate);
				
				int rowsinserted = statement.executeUpdate();
				if(rowsinserted > 0) {
				    JOptionPane.showMessageDialog(null, "Thanks For Your Registration !!");
				    dispose();
				    new ReceptionPage();
				}else {
				    JOptionPane.showMessageDialog(null, "ERROR!!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			 }catch(Exception ex){
			    	JOptionPane.showMessageDialog(null, ex.getMessage());
			 }
	 }

}
