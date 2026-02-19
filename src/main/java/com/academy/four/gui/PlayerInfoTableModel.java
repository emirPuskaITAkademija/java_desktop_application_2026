package com.academy.four.gui;

import com.academy.four.dao.player.PlayerInfo;
import com.academy.four.dao.player.PlayerInfoDao;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
//JTable GUI ----      data, kako će biti prikazani      ----- DB PlayerInfoDao
public class PlayerInfoTableModel extends AbstractTableModel {

    private final List<String> columnNames;
    private final List<PlayerInfo> rawData;

    public PlayerInfoTableModel() {
        PlayerInfoDao playerInfoDao = new PlayerInfoDao();
        this.rawData = playerInfoDao.findAll();
        this.columnNames = playerInfoDao.getColumnNames();
    }

    @Override
    public int getRowCount() {
        return rawData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlayerInfo playerInfo = rawData.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> playerInfo.getId();
            case 1 -> playerInfo.getFirstName();
            case 2 -> playerInfo.getLastName();
            case 3 -> playerInfo.getSport();
            case 4 -> playerInfo.getYears();
            case 5 -> playerInfo.isVegetarian();
            case 6 -> playerInfo.getColor();
            default -> "";
        };
    }


    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        String columnName = columnNames.get(columnIndex);
        return switch (columnName) {
            case "id" -> Integer.class;
            case "first_name" -> String.class;
            case "last_name" -> String.class;
            case "sport" -> String.class;
            case "years" -> Integer.class;
            case "vegetarian" -> Boolean.class;
            case "color" -> Color.class;
            default -> super.getColumnClass(columnIndex);
        };
    }
}