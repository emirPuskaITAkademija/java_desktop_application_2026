package com.academy.three.combo;

import com.academy.one.icon.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxPanel2 extends JPanel {
    private final String[] animalArray = {"Bird", "Cat", "Dog", "Pig", "Rabbit"};
    private final IconLoader iconLoader = new IconLoader(ComboBoxPanel2.class);
    private final JLabel pictureLabel = new JLabel();

    public ComboBoxPanel2() {
        super(new BorderLayout());
        JComboBox<String> animalComboBox = new JComboBox<>(animalArray);
        AnimalActionListener animalActionListener = new AnimalActionListener();
        animalComboBox.addActionListener(animalActionListener);
        pictureLabel.setPreferredSize(new Dimension(177, 132));
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);
        pictureLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        String selectedAnimal = (String) animalComboBox.getSelectedItem();
        updatePictureAccordingToSelectedAnimal(selectedAnimal);

        add(animalComboBox, BorderLayout.PAGE_START);
        add(pictureLabel, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void updatePictureAccordingToSelectedAnimal(String selectedAnimal) {
        String pictureRelativePath = "animals/%s.gif".formatted(selectedAnimal);
        Icon icon = iconLoader.loadIcon(pictureRelativePath);
        pictureLabel.setIcon(icon);
    }

    /**
     * JComboBox<String> <------- ActionEvent event -------> ActionListener
     *
     */
    private class AnimalActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JComboBox<String> animalComboBox = (JComboBox<String>) event.getSource();
            String selectedAnimal = (String) animalComboBox.getSelectedItem();
            updatePictureAccordingToSelectedAnimal(selectedAnimal);
        }
    }
}
