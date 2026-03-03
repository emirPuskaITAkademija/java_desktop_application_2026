package com.academy.four.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        JLabel colorLabel = new JLabel();
        Color newColor = (Color) value;
        colorLabel.setBackground(newColor);
        colorLabel.setOpaque(true);//background se neće vidjeti
        colorLabel.setText("");
        String tooltipText = "RGB value: %s, %s, %s".formatted(newColor.getRed(), newColor.getGreen(), newColor.getBlue());
        colorLabel.setToolTipText(tooltipText);
        colorLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        return colorLabel;
    }
}
