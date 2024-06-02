package Managementsys;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;;


public class UpdatePage extends JFrame implements ActionListener{
	
	
	 private static final long serialVersionUID = 1L;
	 private Connection con;
	 private JPanel contentPane;
	 private JLabel username, regid, contact_no, room_no, roomtype, arr_date, dep_date;
	 private JTextField t,t1,t2,t3,t4,t5;
	 private JButton submit_btn, back_btn;
	 private JComboBox<String> cb;
	 int id;
	
	 public UpdatePage(String[] rowData, int id){
		
		    this.id = id;
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
	        cb.setSelectedItem(rowData[5]);
	        
	        // name field
	        t = new JTextField(); 
	        t.setBounds(217, 80, 194, 36);
	        contentPane.add(t);
	        t.setText(rowData[1]);
	        
	        // registration id field
	        t1 = new JTextField(); 
	        t1.setBounds(217, 127, 194, 33);
	        contentPane.add(t1);
	        t1.setText(rowData[2]);
	        
	        // contact no. field
	        t2 = new JTextField(); 
	        t2.setBounds(217, 174, 194, 33);
	        contentPane.add(t2);
	        t2.setText(rowData[3]);
	        
	        // room no. field
	        t3 = new JTextField(); 
	        t3.setBounds(217, 221, 194, 33);
	        contentPane.add(t3);
	        t3.setText(rowData[4]);
	        
	        // arrival date field
	        t4 = new JTextField(); 
	        t4.setBounds(217, 315, 194, 33);
	        contentPane.add(t4);
	        t4.setText(rowData[6]);
	        
	        // departure date field
	        t5 = new JTextField(); 
	        t5.setBounds(217, 362, 194, 33);
	        contentPane.add(t5);
	        t5.setText(rowData[7]);
	        
	        back_btn = new JButton("←");
		    back_btn.setBounds(180, 409, 50, 33);
		    contentPane.add(back_btn);
	        
	        submit_btn = new JButton("Submit");
	        submit_btn.setBounds(230, 409, 160, 33);
	        contentPane.add(submit_btn);
	        
	        submit_btn.addActionListener(this);
	        
	        
	        contentPane.setBackground(Color.LIGHT_GRAY);
	        setTitle("Update Page");
	        setResizable(false);
	        setVisible(true);
	        
	        back_btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new ReceptionPage();
	                dispose();
	            }
	        });
	      
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
		    if(con != null) {
		    	String qry_update = "UPDATE HMS SET name = ?, regID = ?, contactNO = ?, roomNo = ?, roomType = ?, arrivalDate = ?, departureDate = ? WHERE id = ?";
		    	PreparedStatement statement = con.prepareStatement(qry_update);
		    	
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
				statement.setInt(8, id);
				
				
		        int rowsAffected = statement.executeUpdate();
		        if(rowsAffected > 0) {
		        	JOptionPane.showMessageDialog(null, "Status Updated Successfully");
		        }else {
		        	JOptionPane.showMessageDialog(null, "No record found with id: " + id);
		        }
		        
		        //statement.close();
		    
		    }
		    
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		 }
		new ViewPage();
		dispose();
		
	}



}
