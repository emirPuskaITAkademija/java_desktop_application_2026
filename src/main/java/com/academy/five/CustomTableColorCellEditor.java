package com.academy.five;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Kroz ovu klasu ću omogućiti da se edituje vrijednost Color.class tipa
 *
 * AbstractCellEditor : -> on upravlja životnim ciklusom editovanja jedne ćelije.
 * <li>1. implementaciju CellEditor interfejsa</li>
 * <li>2. podršku fireEditingStopped()</li>
 * <li>3. podršku za fireEditingCancelled</li>
 *
 *
 * TableCellEditor - swing koristi da zna:
 * <li>1. koju komponentu da prikaže kada ćelija uđe u EDIT MODE - JButton</li>
 * <li>2. kako da uzme novu vrijednost iz te komponente</li>
 */
public class CustomTableColorCellEditor extends AbstractCellEditor implements TableCellEditor {


    private Color currentColor;
    private final JButton button = new JButton();

    public CustomTableColorCellEditor() {
        button.setBorderPainted(true);
        button.addActionListener(this::onButtonClick);
    }


    private void onButtonClick(ActionEvent event) {
       Color newColor =  JColorChooser.showDialog(button, "Choose Dialog Color", currentColor);
        if(newColor != null){
            currentColor = newColor;
        }
        fireEditingStopped();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        currentColor = (Color) value;
        button.setBackground(currentColor);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return currentColor;
    }
}
