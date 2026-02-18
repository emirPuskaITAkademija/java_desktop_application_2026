package com.academy.three;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

/**
 * JComboBox<String>[GUI control]  <----- ActionEvent event ----> DateTimePatternComboBoxListener [osluškivač koji reaguje
 * na promjene stanja u GUI control]
 *
 */

public class DateTimePatternComboBoxListener implements ActionListener {

    private final Consumer<String> selectedPatternConsumer;

    public DateTimePatternComboBoxListener(Consumer<String> selectedPatternConsumer) {
        this.selectedPatternConsumer = selectedPatternConsumer;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<String> patternComboBox = (JComboBox<String>) event.getSource();
        String selectedPattern =(String) patternComboBox.getSelectedItem();
        selectedPatternConsumer.accept(selectedPattern);
    }
}
