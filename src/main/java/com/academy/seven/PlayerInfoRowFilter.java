package com.academy.seven;

import com.academy.four.gui.PlayerInfoTableModel;

import javax.swing.*;

public class PlayerInfoRowFilter extends RowFilter<PlayerInfoTableModel, Integer> {

    private final String searchText;

    public PlayerInfoRowFilter(String searchText) {
        this.searchText = searchText;
    }

    //"K", "Kr"...
    @Override
    public boolean include(Entry<? extends PlayerInfoTableModel, ? extends Integer> entry) {
        //Kolone po kojim je uključen filter
        String firstName = entry.getStringValue(1);
        String lastName = entry.getStringValue(2);
        String sport =  entry.getStringValue(3);
        return firstName.contains(searchText) || lastName.contains(searchText) || sport.contains(searchText);
    }
}
