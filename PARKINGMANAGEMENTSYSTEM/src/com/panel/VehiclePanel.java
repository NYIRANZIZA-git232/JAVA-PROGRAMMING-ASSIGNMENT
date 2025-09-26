package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.util.db.ParkingDB;

public class VehiclePanel extends JPanel implements ActionListener {
    JTextField idTxt = new JTextField();
    JTextField plateTxt = new JTextField();
    JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Car", "Motorcycle", "Truck", "SUV"});
    JTextField colorTxt = new JTextField();
    JTextField brandTxt = new JTextField();
    
    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");
    
    JTable table;
    DefaultTableModel model;
    
    public VehiclePanel() {
        setLayout(null);
        String[] labels = {"Vehicle ID", "Plate Number", "Type", "Color", "Brand"};
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 900, 400);
        
        int y = 20;
        addField("Vehicle ID", idTxt, y); y += 30;
        addField("Plate Number", plateTxt, y); y += 30;
        
        JLabel typeLabel = new JLabel("Vehicle Type");
        typeLabel.setBounds(20, y, 80, 25);
        typeCombo.setBounds(100, y, 150, 25);
        add(typeLabel); add(typeCombo); y += 30;
        
        addField("Color", colorTxt, y); y += 30;
        addField("Brand", brandTxt, y);
        
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
                String sql = "INSERT INTO vehicles (platenumber, vehicletype, color, brand) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, plateTxt.getText());
                ps.setString(2, typeCombo.getSelectedItem().toString());
                ps.setString(3, colorTxt.getText());
                ps.setString(4, brandTxt.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
            }
            else if (e.getSource() == updateBtn) {
                String sql = "UPDATE vehicles SET platenumber=?, vehicletype=?, color=?, brand=? WHERE vehicleid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, plateTxt.getText());
                ps.setString(2, typeCombo.getSelectedItem().toString());
                ps.setString(3, colorTxt.getText());
                ps.setString(4, brandTxt.getText());
                ps.setInt(5, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Vehicle updated successfully!");
            }
            else if (e.getSource() == deleteBtn) {
                String sql = "DELETE FROM vehicles WHERE vehicleid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Vehicle deleted successfully!");
            }
            else if (e.getSource() == loadBtn) {
                model.setRowCount(0);
                String sql = "SELECT * FROM vehicles";
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("vehicleid"),
                        rs.getString("platenumber"),
                        rs.getString("vehicletype"),
                        rs.getString("color"),
                        rs.getString("brand")
                    });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}