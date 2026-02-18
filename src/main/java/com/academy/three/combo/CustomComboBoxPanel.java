package com.academy.three.combo;

import com.academy.one.icon.IconLoader;

import javax.swing.*;
import java.awt.*;

public class CustomComboBoxPanel extends JPanel {
    private final Icon[] animalImages;
    private final String[] animalNames = {"Bird", "Cat", "Dog", "Pig", "Rabbit"};

    public CustomComboBoxPanel() {
        super(new BorderLayout());
        //zauzeo prazan prostor u koji ću ubaciti Icon images koji odgovoraju imenima niza animalNames
        this.animalImages = new Icon[animalNames.length];
        IconLoader iconLoader = new IconLoader(CustomComboBoxPanel.class);
        Integer[] animalIds = new Integer[animalNames.length];
        for(int i = 0; i < animalNames.length; i++){
            animalIds[i] = i;
            String animalName =  animalNames[i];
            String relativePath = "animals/%s.gif".formatted(animalName);
            Icon animalIcon = iconLoader.loadIcon(relativePath);
            animalImages[i] = animalIcon;
        }
        //Parametrizovati sa Integer
        JComboBox<Integer> animalComboBox = new JComboBox<>(animalIds);
        IconTextComboBoxRendered iconTextComboBoxRendered = new IconTextComboBoxRendered();
        animalComboBox.setRenderer(iconTextComboBoxRendered);

        add(animalComboBox, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private class IconTextComboBoxRendered implements ListCellRenderer<Integer> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Integer> list,
                                                      Integer index,
                                                      int selectedIndex,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            Color backgroundColor = isSelected ? list.getSelectionBackground() : list.getBackground();
            label.setBackground(backgroundColor);
            Color foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
            label.setForeground(foregroundColor);
            Font font = list.getFont();
            Font boldFont = font.deriveFont(Font.BOLD, 24f);
            label.setFont(boldFont);

            Icon selectedIcon = animalImages[index];
            String animalName = animalNames[index];

            label.setIcon(selectedIcon);
            label.setText(animalName);

            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            return label;
        }
    }
}
