package com.academy.seven;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchPlayerDocumentListener implements DocumentListener {

    private final Runnable runnable;

    public SearchPlayerDocumentListener(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        runnable.run();//applyFilter
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        runnable.run();//applyFilter
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        runnable.run();//applyFilter
    }
}
