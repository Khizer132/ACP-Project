package Managementsys;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame {

	
	private static final long serialVersionUID = 1L;
	public static final int H_FRAME = 300;
    public static final int W_FRAME = 450;
    private JPanel contentPane;
    private JButton button_login;
    private JLabel username, password, label_icon, label_errorText;
    private JTextField userfield;
    private JPasswordField passfield;

    public LoginPage() {

        super("Login");
        setSize(W_FRAME, H_FRAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        GUI();
    }

    private void GUI() {

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setSize(450, 300);
        setResizable(false);
        
        
        // user name label
        username = new JLabel("Username");
        username.setBounds(55,119,64,23);
        contentPane.add(username);
        
        //password label
        password = new JLabel("Password");
        password.setBounds(55,159,64,23);
        contentPane.add(password);
        
        // user name field
        userfield = new JTextField("admin"); 
        userfield.setBounds(130,120,86,20);
        contentPane.add(userfield);
        
        //password field
        passfield = new JPasswordField("12345"); 
        passfield.setBounds(130,160,86,20);
        contentPane.add(passfield);
        
       

        
        userfield.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passfield.requestFocus();
            }
        });

       
        passfield.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button_login.doClick();
            }
        });

        button_login = new JButton("Login");
        button_login.setFocusPainted(false);
        button_login.setBounds(260, 138, 89, 23);
        button_login.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                if (validateCredentials(userfield.getText(), passfield.getPassword())) {
                	JOptionPane.showMessageDialog(null, "Login Successfully");
                	dispose();
                	new ReceptionPage();
                } else {
                    label_errorText.setText("Invalid username or password");
                }
               
            }
        });
        contentPane.add(button_login);

        try {
            // Attempt to load the icon image
            label_icon = new JLabel(new ImageIcon("src/Managementsys/img1.jpg"));
            label_icon.setBounds(userfield.getX() + 20, userfield.getY() - 100, 72, 72);
            contentPane.add(label_icon);
        } 
        catch (Exception e) {
            System.out.println("Error loading icon image: " + e.getMessage());
        }

        label_errorText = new JLabel();
        label_errorText.setForeground(Color.RED);
        label_errorText.setBounds(75,199,200,23);
        contentPane.add(label_errorText);

        setContentPane(contentPane);
        setTitle("Royal Oasis Hotel");

    }

 
    private boolean validateCredentials(String username, char[] password) {
        
        return username.equals("admin") && new String(password).equals("12345"); 
    }

   
}
