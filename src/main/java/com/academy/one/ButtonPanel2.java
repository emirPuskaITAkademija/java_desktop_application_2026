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
public class ButtonPanel2 extends JPanel {
    private final IconLoader iconLoader = new IconLoader(ButtonPanel2.class);
    private final JButton disableButton;
    private final JButton middleButton;
    private final JButton enableButton;

    public ButtonPanel2() {
        Icon rightIcon = iconLoader.loadIcon("right.gif");
        ActionListener disableActionListener = this::onButtonClick;
        this.disableButton = new JButton("Disable Button", rightIcon);
        this.disableButton.setMnemonic(KeyEvent.VK_D);
        this.disableButton.setActionCommand("disable");
        this.disableButton.addActionListener(disableActionListener);
        this.disableButton.setHorizontalTextPosition(SwingConstants.LEADING);
        this.disableButton.setVerticalTextPosition(SwingConstants.CENTER);


        Icon middleIcon = iconLoader.loadIcon("middle.gif");
        this.middleButton = new JButton("Middle Button", middleIcon);
        this.middleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.middleButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        Icon leftIcon = iconLoader.loadIcon("left.gif");
        ActionListener actionListener = this::onButtonClick;
        this.enableButton = new JButton("Enable Button", leftIcon);
        this.enableButton.setMnemonic(KeyEvent.VK_E);
        this.enableButton.setActionCommand("enable");
        this.enableButton.addActionListener(actionListener);

        add(disableButton);
        add(middleButton);
        add(enableButton);
    }


    // ActionListener <---- ActionEvent ------> JButton
    /**
     * Ovaj blok koda metode onButtonClick će se pozivati i na disableButton i na enableButton CLICK.
     * <p>
     *     Pitanje: Kako ćemo uraditi distinkciju toga gdje se desio CLICK : na enableButton ili na disableButton ?
     * </p>
     * @param actionEvent
     */
    private void onButtonClick(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if("disable".equals(command)) {
            this.middleButton.setEnabled(false);
            this.disableButton.setEnabled(false);
            this.enableButton.setEnabled(true);
        }else{
            this.middleButton.setEnabled(true);
            this.disableButton.setEnabled(true);
            this.enableButton.setEnabled(false);
        }
    }
}
