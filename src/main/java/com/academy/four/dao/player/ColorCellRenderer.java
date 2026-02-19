package com.academy.four.dao.player;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        JLabel label = new JLabel();
        Color newColor = (Color) value;
        label.setBackground(newColor);
        label.setOpaque(true);//background se neće vidjeti
        label.setText("");
        return label;

    }
}
