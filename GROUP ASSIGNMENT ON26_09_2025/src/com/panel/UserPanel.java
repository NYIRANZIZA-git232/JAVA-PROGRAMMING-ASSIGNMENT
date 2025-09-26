package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.util.db.ParkingDB;

public class UserPanel extends JPanel implements ActionListener {
    JTextField idTxt = new JTextField();
    JTextField nameTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JTextField phoneTxt = new JTextField();
    JTextField emailTxt = new JTextField();
    JComboBox<String> roleCombo = new JComboBox<>(new String[]{"admin", "attendant", "manager"});
    
    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");
    
    JTable table;
    DefaultTableModel model;
    
    public UserPanel() {
        setLayout(null);
        String[] labels = {"ID", "Username", "Password", "Phone", "Email", "Role"};
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 900, 400);
        
        int y = 20;
        addField("User ID", idTxt, y); y += 30;
        addField("Username", nameTxt, y); y += 30;
        addField("Password", passTxt, y); y += 30;
        addField("Phone", phoneTxt, y); y += 30;
        addField("Email", emailTxt, y); y += 30;
        
        JLabel roleLabel = new JLabel("Role");
        roleLabel.setBounds(20, y, 80, 25);
        roleCombo.setBounds(100, y, 150, 25);
        add(roleLabel);
        add(roleCombo);
        y += 30;
        
        addButtons();
        add(sp);
    }
    
    private void addField(String lbl, JComponent txt, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 80, 25);
        txt.setBounds(100, y, 150, 25);
        add(l);
        add(txt);
    }
    
    private void addButtons() {
        addBtn.setBounds(300, 20, 100, 30);
        updateBtn.setBounds(300, 60, 100, 30);
        deleteBtn.setBounds(300, 100, 100, 30);
        loadBtn.setBounds(300, 140, 100, 30);
        
        add(addBtn); add(updateBtn); add(deleteBtn); add(loadBtn);
        
        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        try (Connection con = ParkingDB.getConnection()) {
            if (e.getSource() == addBtn) {
                String sql = "INSERT INTO users (username, password, phone, email, role) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nameTxt.getText());
                ps.setString(2, new String(passTxt.getPassword()));
                ps.setString(3, phoneTxt.getText());
                ps.setString(4, emailTxt.getText());
                ps.setString(5, roleCombo.getSelectedItem().toString());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User added successfully!");
            }
            else if (e.getSource() == updateBtn) {
                String sql = "UPDATE users SET username=?, password=?, phone=?, email=?, role=? WHERE userid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nameTxt.getText());
                ps.setString(2, new String(passTxt.getPassword()));
                ps.setString(3, phoneTxt.getText());
                ps.setString(4, emailTxt.getText());
                ps.setString(5, roleCombo.getSelectedItem().toString());
                ps.setInt(6, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User updated successfully!");
            }
            else if (e.getSource() == deleteBtn) {
                String sql = "DELETE FROM users WHERE userid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
            }
            else if (e.getSource() == loadBtn) {
                model.setRowCount(0);
                String sql = "SELECT * FROM users";
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("role")
                    });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}