package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.util.db.ParkingDB;

public class ParkingSlotPanel extends JPanel implements ActionListener {
    JTextField idTxt = new JTextField();
    JTextField slotTxt = new JTextField();
    JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Regular", "Disabled", "VIP"});
    JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Available", "Occupied", "Maintenance"});
    JTextField rateTxt = new JTextField();
    
    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");
    
    JTable table;
    DefaultTableModel model;
    
    public ParkingSlotPanel() {
        setLayout(null);
        String[] labels = {"Slot ID", "Slot Number", "Type", "Status", "Hourly Rate"};
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 900, 400);
        
        int y = 20;
        addField("Slot ID", idTxt, y); y += 30;
        addField("Slot Number", slotTxt, y); y += 30;
        
        JLabel typeLabel = new JLabel("Slot Type");
        typeLabel.setBounds(20, y, 80, 25);
        typeCombo.setBounds(100, y, 150, 25);
        add(typeLabel); add(typeCombo); y += 30;
        
        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(20, y, 80, 25);
        statusCombo.setBounds(100, y, 150, 25);
        add(statusLabel); add(statusCombo); y += 30;
        
        addField("Hourly Rate", rateTxt, y);
        
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
                String sql = "INSERT INTO parkingslots (slotnumber, slottype, status, hourlyrate) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, slotTxt.getText());
                ps.setString(2, typeCombo.getSelectedItem().toString());
                ps.setString(3, statusCombo.getSelectedItem().toString());
                ps.setDouble(4, Double.parseDouble(rateTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Parking slot added successfully!");
            }
            else if (e.getSource() == updateBtn) {
                String sql = "UPDATE parkingslots SET slotnumber=?, slottype=?, status=?, hourlyrate=? WHERE slotid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, slotTxt.getText());
                ps.setString(2, typeCombo.getSelectedItem().toString());
                ps.setString(3, statusCombo.getSelectedItem().toString());
                ps.setDouble(4, Double.parseDouble(rateTxt.getText()));
                ps.setInt(5, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Parking slot updated successfully!");
            }
            else if (e.getSource() == deleteBtn) {
                String sql = "DELETE FROM parkingslots WHERE slotid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Parking slot deleted successfully!");
            }
            else if (e.getSource() == loadBtn) {
                model.setRowCount(0);
                String sql = "SELECT * FROM parkingslots";
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("slotid"),
                        rs.getString("slotnumber"),
                        rs.getString("slottype"),
                        rs.getString("status"),
                        rs.getDouble("hourlyrate")
                    });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}