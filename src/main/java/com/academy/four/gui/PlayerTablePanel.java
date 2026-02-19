package com.academy.four.gui;

import com.academy.four.dao.player.ColorCellRenderer;

import javax.swing.*;
import java.awt.*;
// JTable <--> PlayerInfoTableModel(AbstractTableModel) <--> PlayerInfoDao

// MODEL - VIEW
public class PlayerTablePanel extends JPanel {

    public PlayerTablePanel() {
        PlayerInfoTableModel tableModel = new PlayerInfoTableModel();
        JTable table = new JTable(tableModel);
        ColorCellRenderer colorCellRenderer = new ColorCellRenderer();
        table.setDefaultRenderer(Color.class, colorCellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
