package com.academy.five.action;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton deleteButton = ActionButtonFactory.DELETE.getButton();
//        JButton addButton = ActionButtonFactory.ADD.getButton();
        JPanel panel = new JPanel();
        panel.add(deleteButton);
//        panel.add(addButton);
        return panel;
    }
}
