package com.academy.five;

import com.academy.five.action.ActionCellEditor;
import com.academy.five.action.ActionCellRenderer;
import com.academy.five.action.ActionColumnModel;
import com.academy.four.dao.player.PlayerInfo;
import com.academy.four.gui.PlayerInfoTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;

// JTable <--> PlayerInfoTableModel(AbstractTableModel) <--> PlayerInfoDao
//JFrame - BorderLayout
//JPanel - FlowLayout
// MODEL - VIEW


// JTable <------ TableModel(PlayerInfoTableModel) -----> DAO
public class PlayerTablePanel2 extends JPanel {

    public PlayerTablePanel2() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        PlayerInfoTableModel tableModel = new PlayerInfoTableModel();
        JTable table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setDefaultRenderer(Color.class, new CustomTableColorCellRenderer());
        table.setDefaultEditor(Color.class, new CustomTableColorCellEditor());

        table.setDefaultRenderer(ActionColumnModel.class, new ActionCellRenderer());
        table.setDefaultEditor(ActionColumnModel.class, new ActionCellEditor());

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(1).setPreferredWidth(75);
        columnModel.getColumn(2).setPreferredWidth(75);
        columnModel.getColumn(3).setPreferredWidth(75);
        columnModel.getColumn(4).setPreferredWidth(25);

        JScrollPane scrollPane = new JScrollPane(table);


        JPanel formPanel = createForm(tableModel);

        add(scrollPane);
        add(formPanel);
    }

    private JPanel createForm(PlayerInfoTableModel tableModel) {

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JTextField firstNameField = new JTextField(12);
        JTextField lastNameField = new JTextField(12);
        JTextField sportField = new JTextField(12);
        JSpinner yearsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JCheckBox vegetarianCheck = new JCheckBox("Vegetarian");

        final Color[] selectedColor = {Color.WHITE};

        JButton colorButton = new JButton("Pick color");
        colorButton.addActionListener(e -> showColorChoserPopup(formPanel, selectedColor, colorButton));

        JButton addButton = new JButton("Add player");

        int row = 0;
        row = addRow(formPanel, gridBagConstraints, row, "First name:", firstNameField);
        row = addRow(formPanel, gridBagConstraints, row, "Last name:", lastNameField);
        row = addRow(formPanel, gridBagConstraints, row, "Sport:", sportField);
        row = addRow(formPanel, gridBagConstraints, row, "Years:", yearsSpinner);
        row = addRow(formPanel, gridBagConstraints, row, "Diet:", vegetarianCheck);
        row = addRow(formPanel, gridBagConstraints, row, "Color:", colorButton);

        // Add dugme preko cijele širine
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = row;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1;
        formPanel.add(addButton, gridBagConstraints);

        return formPanel;
    }

    private static void showColorChoserPopup(JPanel formPanel, Color[] selectedColor, JButton colorButton) {
        Color newColor = JColorChooser.showDialog(
                formPanel,
                "Choose player color",
                selectedColor[0]
        );

        if (newColor != null) {
            selectedColor[0] = newColor;
            colorButton.setBackground(newColor);
            colorButton.setOpaque(true);
            colorButton.setBorderPainted(false);
        }
    }

    private int addRow(JPanel panel,
                       GridBagConstraints c,
                       int row,
                       String labelText,
                       JComponent component) {

        c.gridy = row;

        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0;
        panel.add(new JLabel(labelText), c);

        c.gridx = 1;
        c.weightx = 1;
        panel.add(component, c);

        return row + 1;
    }

}
