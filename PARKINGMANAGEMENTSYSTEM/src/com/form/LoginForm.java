package com.form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.util.db.ParkingDB;

public class LoginForm extends JFrame implements ActionListener {
    
    JLabel userLabel = new JLabel("Username:");
    JLabel passLabel = new JLabel("Password:");
    JTextField userText = new JTextField();
    JPasswordField passText = new JPasswordField();
    
    JButton loginBtn = new JButton("Login");
    JButton cancelBtn = new JButton("Cancel");
    
    public LoginForm() {
        setTitle("Parking Management System - Login");
        setBounds(100, 100, 400, 300);
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        
        userLabel.setBounds(50, 50, 80, 25);
        userText.setBounds(140, 50, 150, 25);
        
        passLabel.setBounds(50, 90, 80, 25);
        passText.setBounds(140, 90, 150, 25);
        
        loginBtn.setBounds(80, 140, 100, 30);
        cancelBtn.setBounds(200, 140, 100, 30);
        
        add(userLabel);
        add(userText);
        add(passLabel);
        add(passText);
        add(loginBtn);
        add(cancelBtn);
        
        loginBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) {
            System.exit(0);
        }
        
        if (e.getSource() == loginBtn) {
            try (Connection con = ParkingDB.getConnection()) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setString(1, userText.getText());
                ps.setString(2, new String(passText.getPassword()));
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    int userid = rs.getInt("userid");
                    dispose();
                    new ParkingManagement(role, userid);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection error!");
            }
        }
    }
}