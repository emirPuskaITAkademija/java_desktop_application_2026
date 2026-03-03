package com.academy.five.action;

import com.academy.four.dao.player.PlayerInfo;
import com.academy.four.gui.PlayerInfoTableModel;
import com.academy.six.PlayerInfoEditFrame;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {

    private ActionColumnModel currentValue;

    @Override
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        this.currentValue = (ActionColumnModel) value;
        JPanel panel = new JPanel();
        panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        JButton deleteButton = ActionButtonFactory.DELETE.getButton(e -> onDeleteButtonClick(table));
        JButton editButton = ActionButtonFactory.EDIT.getButton(e -> onEditButtonClick(table));

        panel.add(deleteButton);
        panel.add(editButton);
        return panel;
    }


    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

    private void onDeleteButtonClick(JTable table) {
        PlayerInfoTableModel playerInfoTableModel = (PlayerInfoTableModel) table.getModel();
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure that you want to delete this player ? "
                , "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            PlayerInfo playerInfo = currentValue.getPlayerInfo();
            playerInfoTableModel.deleteExistingPlayer(playerInfo);
        }
    }

    private void onEditButtonClick(JTable table) {
        PlayerInfoTableModel playerInfoTableModel = (PlayerInfoTableModel) table.getModel();
        PlayerInfo playerInfo = currentValue.getPlayerInfo();
        PlayerInfoEditFrame playerInfoEditFrame = new PlayerInfoEditFrame(playerInfo, playerInfoTableModel::updatePlayerInfo);
        playerInfoEditFrame.setVisible(true);
    }
}
