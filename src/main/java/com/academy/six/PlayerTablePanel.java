package com.academy.six;

import com.academy.five.CustomTableColorCellEditor;
import com.academy.five.CustomTableColorCellRenderer;
import com.academy.five.action.ActionCellEditor;
import com.academy.five.action.ActionCellRenderer;
import com.academy.five.action.ActionColumnModel;
import com.academy.four.dao.player.PlayerInfo;
import com.academy.four.gui.PlayerInfoTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

// JTable <---- PlayerInfoTableModel(TableModel) ---> podacima PlayerInfoDao
// PlayerFormPanel
public class PlayerTablePanel extends JPanel {

    private final PlayerInfoTableModel tableModel;
    private final JTable table;

    public PlayerTablePanel() {
        this.tableModel = new PlayerInfoTableModel();
        this.table = createTable(tableModel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        table.setFillsViewportHeight(true);
        add(new JScrollPane(table));
        add(new PlayerFormPanel(tableModel::addNewPlayerInfo));
    }

    private JTable createTable(PlayerInfoTableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.setRowHeight(35);

        table.setDefaultRenderer(Color.class, new CustomTableColorCellRenderer());
        table.setDefaultEditor(Color.class, new CustomTableColorCellEditor());

        table.setDefaultRenderer(ActionColumnModel.class, new ActionCellRenderer());
        table.setDefaultEditor(ActionColumnModel.class, new ActionCellEditor());

        //JTable ---> TableColumnModel --> TableColumn
        TableColumnModel columnModel = table.getColumnModel();
        configureColumnWidths(columnModel, 220, 75, 75, 75, 50, 25, 60);

        return table;
    }
    //JTable ---> TableColumnModel --> TableColumn
    //varargs ---> varijablini broj parametara
    private void configureColumnWidths(TableColumnModel columnModel, int...widths) {
        int min = Math.min(widths.length, columnModel.getColumnCount());
        for(int i = 0; i < min; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(widths[i]);
        }
    }


//    //        onAddPlayerConsumer.accept(playerInfo);
//    private void acceptPlayerInfo(PlayerInfo playerInfo) {
//        tableModel.addNewPlayerInfo(playerInfo);
//    }
}
