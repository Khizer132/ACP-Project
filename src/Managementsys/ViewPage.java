package Managementsys;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class ViewPage extends JFrame implements ActionListener, MouseListener{
	
	
	private static final long serialVersionUID = 1L;
	private Connection con;
	private JPanel contentPane;
	private JTable datatable;
	private JButton refresh_btn, update_btn, delete_btn, back_btn;
	
	public ViewPage(){
		
		  contentPane = new JPanel();
	      contentPane.setLayout(null);
	      //contentPane.setSize(400,500);
	      setBounds(100, 100, 700, 474);
	      setContentPane(contentPane);
	      
	      // Creating JTable to fetch data
	      String[] columnNames = {};
	      DefaultTableModel model = new DefaultTableModel(null,columnNames);
	      datatable = new JTable(model) ;
	      datatable.setSize(700, 350);
	      datatable.setLocation(0,50);
	      datatable.getTableHeader().setBounds(0, 0, 700, 50);
	      contentPane.add(datatable.getTableHeader());
	      contentPane.add(datatable);
	      
	      refresh_btn = new JButton("Refresh");
	      refresh_btn.setBounds(380, 400, 100, 30);
	      contentPane.add(refresh_btn);
	        
	      update_btn = new JButton("Update");
	      update_btn.setBounds(280, 400, 100, 30);
	      contentPane.add(update_btn);
	      update_btn.setEnabled(false);   
	      
	      delete_btn = new JButton("Delete");
	      delete_btn.setBounds(180, 400, 100, 30); 
	      contentPane.add(delete_btn);// 
	      delete_btn.setEnabled(false);  
	      
	      back_btn = new JButton("←");
	      back_btn.setBounds(0, 400, 50, 30);
	      contentPane.add(back_btn);
	      
	      contentPane.setBackground(Color.LIGHT_GRAY);
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setResizable(false);
	      setVisible(true);
	      
          refresh_btn.addActionListener(this);
	      datatable.addMouseListener(this);
	      
	      back_btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new ReceptionPage();
	                dispose();
	            }
	        });
	      
	      DeleteRecord deleteRecord = new DeleteRecord(delete_btn, datatable);
	      
	      update_btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int row = datatable.getSelectedRow();
	                if (row != -1) { // Check if a row is selected
	                    String[] rowData = new String[datatable.getColumnCount()];
	                    
	                    for (int i = 0; i < datatable.getColumnCount(); i++) {
	                        rowData[i] = (String) datatable.getValueAt(row, i);
	                    }
	                    new UpdatePage(rowData, Integer.parseInt(datatable.getValueAt(row, 0).toString()));
	                    dispose();
	                }
	            }
	        });
	    
		 }
	
	
	public void actionPerformed(ActionEvent e) {
	    String url = "Jdbc:mysql://localhost:3306/acp_project";
	    String username = "root";
	    String password = "khixerimt89@";

	    try {
	        con = DriverManager.getConnection(url, username, password);
	        if (con != null) {
	            String show_qry = "Select * from HMS";
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(show_qry);
	            ResultSetMetaData rs_meta = rs.getMetaData();

	            DefaultTableModel tbl_mdl = (DefaultTableModel) datatable.getModel();
	            tbl_mdl.setRowCount(0); // Clear existing rows

	            while (rs.next()) {
	                String[] colname = new String[rs_meta.getColumnCount()];
	                for (int i = 0; i < rs_meta.getColumnCount(); i++) {
	                    colname[i] = rs_meta.getColumnName(i + 1);
	                }
	                tbl_mdl.setColumnIdentifiers(colname);  // Set column names here

	                String[] row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
	                tbl_mdl.addRow(row);
	            }
	        }
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
	}
	
	public void mouseClicked(MouseEvent e) {
  	  int id= Integer.parseInt(datatable.getValueAt(datatable.getSelectedRow(), 0).toString());
  	  System.out.println(id);
  	  int row = datatable.getSelectedRow();
      if (row != -1){ 
    	  update_btn.setEnabled(true);
    	  delete_btn.setEnabled(true);
      } else {
        
          update_btn.setEnabled(false);
          delete_btn.setEnabled(false);
      }
  
	}

   
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
