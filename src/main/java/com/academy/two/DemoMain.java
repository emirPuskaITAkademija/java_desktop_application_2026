package com.academy.two;

import com.academy.one.ButtonPanel2;

import javax.swing.*;

/**
 * Tri vrste GUI komponenti:
 * <li>1. Kontejneri</li>
 * <li>2. Kontrole </li>
 * <li>3. Layout Manager - on je uvijek povezan s nekim kontejnerom i daje upute kontejneru kako da dijeli
 * svoj prostor na sekcije...da bi kasnije mogao u te sekcije slagati kontrole(JBUtton, JCheckBox)
 * </li>
 */
public class DemoMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DemoMain::createAndShowGUI);
    }

    /**
     * Ovaj method treba da izvršava GUI Thread kako ne bismo blokirali MAIN thread
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("CheckBox Demonstration");
        JPanel buttonPanel = new CheckBoxPanel();
        frame.setContentPane(buttonPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
