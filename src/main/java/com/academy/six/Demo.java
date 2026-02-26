package com.academy.six;

import javax.swing.*;
import java.awt.*;

public class Demo {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(Demo::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Complete Function Table Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayerTablePanel playerTablePanel = new PlayerTablePanel();
        frame.setContentPane(playerTablePanel);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.pack();
        frame.setVisible(true);
    }
}
