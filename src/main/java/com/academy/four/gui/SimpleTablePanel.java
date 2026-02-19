package com.academy.four.gui;

import javax.swing.*;
import java.awt.*;

public class SimpleTablePanel extends JPanel {
    private final String[] columnNames = {"First Name", "Last Name", "Sport", " # of Years", "Vegetarian"};
    private final Object[][] data = {
            {"Milica", "Ivan", "Odbojka", 3, false},
            {"Kristijan", "Birkic", "Vaterpolo", 5, false},
            {"Gorica", "Prezime", "Snowboarding", 3, true},
            {"Aleksandar", "Petković", "Knitting", 7, false},
            {"Stefan", "Bantic", "Fudbal", 5, true},
            {"Elmir", "Korjenic", "Fudbal", 5, false}
    };

    public SimpleTablePanel() {
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
