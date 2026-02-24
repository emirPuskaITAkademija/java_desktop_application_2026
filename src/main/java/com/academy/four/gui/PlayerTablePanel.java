package com.academy.four.gui;

import com.academy.five.CustomTableColorCellRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;
// JTable <--> PlayerInfoTableModel(AbstractTableModel) <--> PlayerInfoDao
//JFrame - BorderLayout
//JPanel - FlowLayout
// MODEL - VIEW
public class PlayerTablePanel extends JPanel {

    public PlayerTablePanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        PlayerInfoTableModel tableModel = new PlayerInfoTableModel();
        JTable table = new JTable(tableModel);
        table.setRowHeight(20);
//        ColorCellRenderer colorCellRenderer = new ColorCellRenderer();
        table.setDefaultRenderer(Color.class, new CustomTableColorCellRenderer());

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(75);
        columnModel.getColumn(2).setPreferredWidth(75);
        columnModel.getColumn(3).setPreferredWidth(75);
        columnModel.getColumn(4).setPreferredWidth(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
