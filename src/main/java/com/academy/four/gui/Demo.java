package com.academy.four.gui;

import javax.swing.*;

public class Demo {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeLater(Demo::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Table Demonstration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        SimpleTablePanel panel = new SimpleTablePanel();
        PlayerTablePanel panel = new PlayerTablePanel();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
