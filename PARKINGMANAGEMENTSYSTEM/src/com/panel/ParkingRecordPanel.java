package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.util.db.ParkingDB;

public class ParkingRecordPanel extends JPanel implements ActionListener {
    JTextField recordIdTxt = new JTextField();
    JTextField vehicleIdTxt = new JTextField();
    JTextField slotIdTxt = new JTextField();
    JTextField attendantIdTxt = new JTextField();
    JTextField entryTimeTxt = new JTextField(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    JTextField exitTimeTxt = new JTextField();
    JTextField amountTxt = new JTextField();
    JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Active", "Completed"});
    
    JButton addBtn = new JButton("Add Entry");
    JButton updateBtn = new JButton("Update Exit");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");
    JButton calculateBtn = new JButton("Calculate Fee");
    
    JTable table;
    DefaultTableModel model;
    
    public ParkingRecordPanel() {
        setLayout(null);
        String[] labels = {"Record ID", "Vehicle ID", "Slot ID", "Attendant ID", "Entry Time", "Exit Time", "Amount", "Status"};
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 250, 950, 350);
        
        int y = 20;
        addField("Record ID", recordIdTxt, y); y += 30;
        addField("Vehicle ID", vehicleIdTxt, y); y += 30;
        addField("Slot ID", slotIdTxt, y); y += 30;
        addField("Attendant ID", attendantIdTxt, y); y += 30;
        addField("Entry Time", entryTimeTxt, y); y += 30;
        addField("Exit Time", exitTimeTxt, y); y += 30;
        addField("Amount", amountTxt, y); y += 30;
        
        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(20, y, 80, 25);
        statusCombo.setBounds(100, y, 150, 25);
        add(statusLabel); add(statusCombo);
        
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
        addBtn.setBounds(300, 20, 120, 30);
        updateBtn.setBounds(300, 60, 120, 30);
        deleteBtn.setBounds(300, 100, 120, 30);
        loadBtn.setBounds(300, 140, 120, 30);
        calculateBtn.setBounds(300, 180, 120, 30);
        
        add(addBtn); add(updateBtn); add(deleteBtn); add(loadBtn); add(calculateBtn);
        
        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
        calculateBtn.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        try (Connection con = ParkingDB.getConnection()) {
            if (e.getSource() == addBtn) {
                String sql = "INSERT INTO parkingrecords (vehicleid, slotid, attendantid, entrytime, status) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(vehicleIdTxt.getText()));
                ps.setInt(2, Integer.parseInt(slotIdTxt.getText()));
                ps.setInt(3, Integer.parseInt(attendantIdTxt.getText()));
                ps.setString(4, entryTimeTxt.getText());
                ps.setString(5, "Active");
                ps.executeUpdate();
                
                // Update slot status to Occupied
                String updateSlot = "UPDATE parkingslots SET status='Occupied' WHERE slotid=?";
                PreparedStatement psSlot = con.prepareStatement(updateSlot);
                psSlot.setInt(1, Integer.parseInt(slotIdTxt.getText()));
                psSlot.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Parking entry recorded successfully!");
            }
            else if (e.getSource() == updateBtn) {
                String sql = "UPDATE parkingrecords SET exittime=?, totalamount=?, status=? WHERE recordid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, exitTimeTxt.getText());
                ps.setDouble(2, Double.parseDouble(amountTxt.getText()));
                ps.setString(3, statusCombo.getSelectedItem().toString());
                ps.setInt(4, Integer.parseInt(recordIdTxt.getText()));
                ps.executeUpdate();
                
                // Update slot status to Available
                String updateSlot = "UPDATE parkingslots SET status='Available' WHERE slotid=?";
                PreparedStatement psSlot = con.prepareStatement(updateSlot);
                psSlot.setInt(1, Integer.parseInt(slotIdTxt.getText()));
                psSlot.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Parking exit updated successfully!");
            }
            else if (e.getSource() == deleteBtn) {
                String sql = "DELETE FROM parkingrecords WHERE recordid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(recordIdTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record deleted successfully!");
            }
            else if (e.getSource() == loadBtn) {
                model.setRowCount(0);
                String sql = "SELECT * FROM parkingrecords";
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("recordid"),
                        rs.getInt("vehicleid"),
                        rs.getInt("slotid"),
                        rs.getInt("attendantid"),
                        rs.getString("entrytime"),
                        rs.getString("exittime"),
                        rs.getDouble("totalamount"),
                        rs.getString("status")
                    });
                }
            }
            else if (e.getSource() == calculateBtn) {
                calculateParkingFee();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    
    private void calculateParkingFee() {
        try {
            if (recordIdTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Record ID first!");
                return;
            }
            
            try (Connection con = ParkingDB.getConnection()) {
                String sql = "SELECT pr.entrytime, ps.hourlyrate FROM parkingrecords pr " +
                           "JOIN parkingslots ps ON pr.slotid = ps.slotid " +
                           "WHERE pr.recordid = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(recordIdTxt.getText()));
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    String entryTimeStr = rs.getString("entrytime");
                    double hourlyRate = rs.getDouble("hourlyrate");
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date entryTime = format.parse(entryTimeStr);
                    Date exitTime = new Date(); // current time
                    
                    long duration = exitTime.getTime() - entryTime.getTime();
                    double hours = duration / (1000.0 * 60 * 60);
                    double amount = Math.ceil(hours) * hourlyRate;
                    
                    exitTimeTxt.setText(format.format(exitTime));
                    amountTxt.setText(String.format("%.2f", amount));
                    statusCombo.setSelectedItem("Completed");
                    
                    JOptionPane.showMessageDialog(this, 
                        String.format("Parking duration: %.2f hours\nAmount: $%.2f", hours, amount));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error calculating fee: " + ex.getMessage());
        }
    }
}