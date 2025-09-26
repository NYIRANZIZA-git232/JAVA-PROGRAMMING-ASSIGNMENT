package com.form;

import java.awt.BorderLayout;
import javax.swing.*;

import com.panel.*;

public class ParkingManagement extends JFrame {
    JTabbedPane tabs = new JTabbedPane();
    
    public ParkingManagement(String role, int userid) {
        setTitle("Parking Management System");
        setSize(1000, 700);
        setLayout(new BorderLayout());
        
        
        if (role.equalsIgnoreCase("admin")) {
            tabs.add("Users", new UserPanel());
            tabs.add("Vehicles", new VehiclePanel());
            tabs.add("Parking Slots", new ParkingSlotPanel());
            tabs.add("Parking Records", new ParkingRecordPanel());
        }
        
        else if (role.equalsIgnoreCase("attendant")) {
            tabs.add("Vehicles", new VehiclePanel());
            tabs.add("Parking Records", new ParkingRecordPanel());
        }
        
        else if (role.equalsIgnoreCase("manager")) {
            tabs.add("Parking Slots", new ParkingSlotPanel());
            tabs.add("Parking Records", new ParkingRecordPanel());
            tabs.add("Users", new UserPanel());
        }
        
        add(tabs, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}