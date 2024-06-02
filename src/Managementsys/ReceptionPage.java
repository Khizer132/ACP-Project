package Managementsys;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ReceptionPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    JLabel title;
    JButton createbtn,readbtn,updatebtn,deletebtn,logoutbtn;


    public ReceptionPage(){

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setBounds(100, 100, 649, 474);
        setContentPane(contentPane);
        setTitle("Reception");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        title = new JLabel("Royal Oasis Hotel");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));
        title.setBounds(206, 100, 300, 36); 
        contentPane.add(title);
        
        
        // CRUD operation Buttons
        createbtn = new JButton("Customer Registration");
        createbtn.setBounds(217, 166, 194, 36);
        contentPane.add(createbtn);

        readbtn = new JButton("View Customer Status");
        readbtn.setBounds(217, 213, 194, 33);
        contentPane.add(readbtn);

        updatebtn = new JButton("Update Customer Status");
        updatebtn.setBounds(217, 256, 194, 33);
        contentPane.add(updatebtn);

        deletebtn = new JButton("Delete Customer Record");
        deletebtn.setBounds(217, 300, 194, 32);
        contentPane.add(deletebtn);

        logoutbtn = new JButton("Logout");
        logoutbtn.setBounds(217, 343, 194, 33);
        contentPane.add(logoutbtn);
        
        // Action Listeners
        createbtn.addActionListener(this);
        readbtn.addActionListener(this);
        updatebtn.addActionListener(this);
        deletebtn.addActionListener(this);
        logoutbtn.addActionListener(this);
        
        contentPane.setBackground(Color.LIGHT_GRAY);
        setResizable(false);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e) {
    	try {
    		if(e.getSource() == createbtn) {
    			new RegistrationPage();
    			
    		}
    		else if(e.getSource() == readbtn || e.getSource() == updatebtn || e.getSource() == deletebtn) {
    			new ViewPage();
    		}
    	
    		else if(e.getSource() == logoutbtn) {
    			dispose();
    		}
    		setVisible(false);
    		
    	}catch(Exception exp) {
    		JOptionPane.showMessageDialog(null, exp.getMessage());
    	}
    	
    }
    
}
