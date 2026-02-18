package com.academy.two;

import com.academy.one.icon.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * <li>
 * 1. JFrame  <--> BorderLayout
 * 2. JPanel  <--> FlowLayout
 * </li>
 * <li>
 * GUI kontrole
 * </li>
 * <p>
 * Neće koristiti defaultno ponašanje nego ćemo ga natjerati da koristi npr. BorderLayout
 *
 */
public class CheckBoxPanel extends JPanel {
    //CheckBoxPanel HAS-A

    private final JCheckBox chinCheckBox = new JCheckBox("Chin");
    private final JCheckBox glassesCheckBox = new JCheckBox("Glasses");
    private final JCheckBox hairCheckBox = new JCheckBox("Hair");
    private final JCheckBox teethCheckBox = new JCheckBox("Teeth");

    private final JLabel pictureLabel = new JLabel();
    //geek-cght.gif
    // Kada neko uradi uncheck sa chinCheckBox kako treba da izgleda vrijednost varijable pictureChoice ?
    // -ght
    // Kada neko uradi check sa chinCheckBox kako treba da izgleda vrijednost varijable pictureChoice ?
    // cght
    private final StringBuffer pictureChoice = new StringBuffer("cght");

    public CheckBoxPanel() {
        this.setLayout(new BorderLayout());

        CheckBoxListener checkBoxListener = new CheckBoxListener();
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(0, 1));
        chinCheckBox.setSelected(true);
        chinCheckBox.addItemListener(checkBoxListener);
        checkBoxPanel.add(chinCheckBox);
        glassesCheckBox.setSelected(true);
        glassesCheckBox.addItemListener(checkBoxListener);
        checkBoxPanel.add(glassesCheckBox);
        hairCheckBox.setSelected(true);
        hairCheckBox.addItemListener(checkBoxListener);
        checkBoxPanel.add(hairCheckBox);
        teethCheckBox.setSelected(true);
        teethCheckBox.addItemListener(checkBoxListener);
        checkBoxPanel.add(teethCheckBox);

        this.add(checkBoxPanel, BorderLayout.LINE_START);
        updatePicture();
        this.add(pictureLabel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void updatePicture() {
        IconLoader iconLoader = new IconLoader(CheckBoxPanel.class);
        //inicijalno će biti učitano geek/geek-cght
        String relativePath = "geek1/geek-" + pictureChoice + ".png";
        Icon imageIcon = iconLoader.loadIcon(relativePath);
        pictureLabel.setIcon(imageIcon);
    }

    /**
     * JCheckBox <-------- event -------> CheckBoxListener(ItemListener)
     */
    private class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            Object source = event.getSource();
            //Pattern matching for switch sa tzv. GUARD-om(when) -> zamjena za instanceOf
            boolean selected = event.getStateChange() == ItemEvent.SELECTED;
            Choice choice = switch (source) {
                case JCheckBox cb when cb == chinCheckBox -> new Choice(0, selected ? 'c' : '-');
                case JCheckBox cb when cb == glassesCheckBox -> new Choice(1, selected ? 'g' : '-');
                case JCheckBox cb when cb == hairCheckBox -> new Choice(2, selected ? 'h' : '-');
                case JCheckBox cb when cb == teethCheckBox -> new Choice(3, selected ? 't' : '-');
                default -> new Choice(0, '-');
            };
            //pictureChoice 'cght' -> index = 0,  cght => SELECTED  -ght => DESELECTED
            //pictureChoice 'cght' -> index = 1 , c-ht
            pictureChoice.setCharAt(choice.index(), choice.slovo());
            updatePicture();
        }


    }
}
