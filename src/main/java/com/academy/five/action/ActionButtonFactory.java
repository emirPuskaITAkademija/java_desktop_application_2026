package com.academy.five.action;

import com.academy.one.icon.IconLoader;

import javax.swing.*;
import java.awt.event.ActionListener;

public enum ActionButtonFactory {

    ADD("Add Player", "player/add-player.png"),
    DELETE("Delete", "player/delete-player.png"),;


    private final String buttonText;
    private final String iconPath;
    private final IconLoader iconLoader = new IconLoader(ActionButtonFactory.class);

    ActionButtonFactory(String buttonText, String iconPath) {
        this.buttonText = buttonText;
        this.iconPath = iconPath;
    }

    public JButton getButton() {
        Icon buttonIcon = iconLoader.loadIcon(iconPath);
        JButton button = new JButton(buttonIcon);
        button.setActionCommand(buttonText);
        button.setOpaque(true);
        return button;
    }

    public JButton getButton(ActionListener actionListener) {
        JButton button = getButton();
        button.addActionListener(actionListener);
        return button;
    }
}
