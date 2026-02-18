package com.academy.three.combo;

import com.academy.three.DateTimePatternComboBoxListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ComboBoxPanel extends JPanel {

    private final String[] dateTimePatterns = {
            "dd MMMMM yyyy",
            "dd.MM.yyyy",
            "dd.MM.yy",
            "MM/dd/yy",
            "MM/dd/yyyy",
            "yyyy-MM-dd",
            "yyyy.MM.dd G 'at' HH:mm:ss z",//2026.02.18 AD at 17:47:15 CET
            "yyyy-MM-dd HH:mm:ss",
            "yyyy.MMMMM.dd G hh:mm aaa" // AM(Ante Meridiem) -> prije podne,  PM(Post Meridiem) -> poslije podne[12:00 23:59]
    };

    private JLabel resultLabel = new JLabel("");
    private String currentSelectedDateTimePattern;

    public ComboBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.currentSelectedDateTimePattern = dateTimePatterns[0];

        JLabel patternLabel1 = new JLabel("Unesite tekstualni pattern datuma/vremena ili");
        JLabel patternLabel2 = new JLabel("odaberite postojeći pattern datuma/vremena");
        //1. GUI kontrola
        JComboBox<String> patternComboBox = new JComboBox<>(dateTimePatterns);
        patternComboBox.setEditable(true);
        //2. Osluškivač ili Listener koji reaguje na promjene vrijednosti u GUI control
        DateTimePatternComboBoxListener patternListener = new DateTimePatternComboBoxListener(this::formatDateTimeAccordingToSelectedPattern);
        //3. Povežemo na način da listener i GUI kontrola znaju jedan za drugog
        patternComboBox.addActionListener(patternListener);
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.PAGE_AXIS));//vertikalno

        patternLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
//        patternPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        patternPanel.add(patternComboBox);


        JLabel patternResultLabel = new JLabel("Trenutni datum/vrijeme formatirano", JLabel.LEADING);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(0, 1));
        resultPanel.add(patternResultLabel);
        //new Color(120, 45, 200) -> R 0 - 255
        resultLabel.setForeground(Color.DARK_GRAY);//postavljam boju teksta
        Border lineBorder = BorderFactory.createLineBorder(Color.black);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        resultLabel.setBorder(compoundBorder);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(currentSelectedDateTimePattern);
        String formattedDate = formatter.format(now);
        resultLabel.setText(formattedDate);
        resultPanel.add(resultLabel);


        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void formatDateTimeAccordingToSelectedPattern(String selectedPatternValue) {
//        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(selectedPatternValue);
        String formattedDate = formatter.format(now);
        resultLabel.setText(formattedDate);
    }
}
