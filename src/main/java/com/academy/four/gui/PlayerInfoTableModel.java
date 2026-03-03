package com.academy.four.gui;

import com.academy.five.action.ActionColumnModel;
import com.academy.four.dao.player.PlayerInfo;
import com.academy.four.dao.player.PlayerInfoDao;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

//JTable GUI ----      data, kako će biti prikazani      ----- DB PlayerInfoDao
// JTable <---------------- PlayerInfoTableModel   -----------------------> PlayerInfoDao
public class PlayerInfoTableModel extends AbstractTableModel {

    private final PlayerInfoDao playerInfoDao = new PlayerInfoDao();
    private final List<String> columnNames;
    private final List<PlayerInfo> rawData;

    public PlayerInfoTableModel() {
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
            case 0 -> new ActionColumnModel(playerInfo);
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
            case "id" -> ActionColumnModel.class;
            case "first_name" -> String.class;
            case "last_name" -> String.class;
            case "sport" -> String.class;
            case "years" -> Integer.class;
            case "vegetarian" -> Boolean.class;
            case "color" -> Color.class;
            default -> super.getColumnClass(columnIndex);
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        String columnName = columnNames.get(columnIndex);
        return true;
    }

    @Override
    public void setValueAt(Object newValue,
                           int rowIndex,
                           int columnIndex) {
        String columnName = columnNames.get(columnIndex);
        if ("id".equals(columnName)) {
            return;
        }
        PlayerInfo playerInfo = rawData.get(rowIndex);
        switch (columnName) {
            case "first_name" -> playerInfo.setFirstName(newValue.toString());
            case "last_name" -> playerInfo.setLastName(newValue.toString());
            case "sport" -> playerInfo.setSport(newValue.toString());
            case "years" -> playerInfo.setYears(Integer.parseInt(newValue.toString()));
            case "vegetarian" -> playerInfo.setVegetarian(Boolean.parseBoolean(newValue.toString()));
            case "color" -> playerInfo.setColor((Color) newValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        playerInfoDao.update(playerInfo);
    }

    public void deleteExistingPlayer(PlayerInfo playerInfo) {
        rawData.remove(playerInfo);
        fireTableDataChanged();
        playerInfoDao.delete(playerInfo);
    }

    public void addNewPlayerInfo(PlayerInfo playerInfo) {
        playerInfoDao.save(playerInfo);
        rawData.add(playerInfo);
        fireTableDataChanged();
    }

    public void updatePlayerInfo(PlayerInfo playerInfo) {
        int index =  rawData.indexOf(playerInfo);
        rawData.set(index,playerInfo);
        playerInfoDao.update(playerInfo);
        fireTableDataChanged();
    }
}