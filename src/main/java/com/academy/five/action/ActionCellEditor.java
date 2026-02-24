package com.academy.five.action;

import com.academy.four.dao.player.PlayerInfo;
import com.academy.four.dao.player.PlayerInfoDao;
import com.academy.four.gui.PlayerInfoTableModel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
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
        JButton deleteButton = ActionButtonFactory.DELETE.getButton(e -> onDeleteButtonClick(table));
//        JButton addButton = ActionButtonFactory.ADD.getButton(this::onAddButtonClick);

        panel.add(deleteButton);
//        panel.add(addButton);
        return panel;
    }


    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

    private void onDeleteButtonClick(JTable table) {
        PlayerInfoTableModel playerInfoTableModel = (PlayerInfoTableModel)table.getModel();
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure that you want to delete this player ? "
                , "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            PlayerInfo playerInfo = currentValue.getPlayerInfo();
            playerInfoTableModel.deleteExistingPlayer(playerInfo);
        }
    }

    private void onAddButtonClick(ActionEvent event) {
        //TODO
    }
}
