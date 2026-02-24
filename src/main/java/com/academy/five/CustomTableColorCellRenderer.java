package com.academy.five;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
//CustomTableColorCellRenderer - TableCellRender, JLabel
public class CustomTableColorCellRenderer extends DefaultTableCellRenderer {

    public CustomTableColorCellRenderer() {
        setOpaque(true);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(8, 8, 8, 8)
        ));
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color newColor = (Color) value;
        setBackground(newColor);
        setText("");
        String tooltipText = "RGB value: %d, %d, %d"
                .formatted(newColor.getRed(),
                        newColor.getGreen(),
                        newColor.getBlue());

        setToolTipText(tooltipText);
        return this;
    }
}
