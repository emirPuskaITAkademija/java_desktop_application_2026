package com.academy.one;

import com.academy.one.icon.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// 1. Kontejner : JPanel ... ButtonPanel
// 2. LayoutManager: FlowLayout => on kaže JPanelu kako će slagati GUI kontrole i to kaže mu sljedeće
//" Doddavaćeš komponente GUI redoslijedom s lijeva na desna a kad ne bude mjesta u jednom redu onda prelomi u novi red "
// 3. GUI kontrole: JButton
public class ButtonPanel extends JPanel {
    private final IconLoader iconLoader = new IconLoader(ButtonPanel.class);
    private final JButton disableButton;
    private final JButton middleButton;
    private final JButton enableButton;

    public ButtonPanel() {
        Icon rightIcon = iconLoader.loadIcon("right.gif");
        DisableActionListener disableActionListener = new DisableActionListener();
        this.disableButton = new JButton("Disable Button", rightIcon);
        this.disableButton.setMnemonic(KeyEvent.VK_D);
        this.disableButton.addActionListener(disableActionListener);
        this.disableButton.setHorizontalTextPosition(SwingConstants.LEADING);
        this.disableButton.setVerticalTextPosition(SwingConstants.CENTER);


        Icon middleIcon = iconLoader.loadIcon("middle.gif");
        this.middleButton = new JButton("Middle Button", middleIcon);
        this.middleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.middleButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        Icon leftIcon = iconLoader.loadIcon("left.gif");
        ActionListener actionListener = this::onEnableButtonClick;
        this.enableButton = new JButton("Enable Button", leftIcon);
        this.enableButton.setMnemonic(KeyEvent.VK_E);
        this.enableButton.addActionListener(actionListener);

        add(disableButton);
        add(middleButton);
        add(enableButton);
    }


    private void onEnableButtonClick(ActionEvent e){
        middleButton.setEnabled(true);
        enableButton.setEnabled(false);
        disableButton.setEnabled(true);
    }

    private class DisableActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            middleButton.setEnabled(false);
            disableButton.setEnabled(false);
            enableButton.setEnabled(true);
        }
    }
}
