package Managementsys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class DeleteRecord implements ActionListener {
    Connection con;
    JButton delete_btn;
    JTable datatable; 

    
    DeleteRecord(JButton delete_btn, JTable datatable) {
        this.delete_btn = delete_btn;
        this.datatable = datatable; 
        this.delete_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/acp_project";
        String username = "root";
        String password = "khixerimt89@";

        try {
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                int row = datatable.getSelectedRow();

                if (row != -1) { 
                    String id = datatable.getValueAt(row, 0).toString();
                    String qry_delete = "DELETE FROM HMS WHERE id = ?";
                    PreparedStatement statement = con.prepareStatement(qry_delete);
                    statement.setString(1, id);
                    int result = statement.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete record");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
