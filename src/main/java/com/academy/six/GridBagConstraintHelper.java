package com.academy.six;

import java.awt.*;

public class GridBagConstraintHelper {
    /**
     * Čista helper metoda za constraints/ograničenje
     * <p>
     *     Usage:
     *     panel.setLayout(..gridBagLayout)
     *     panel.add(component, gbc(0, 1)); -
     *     gbc.gridx -> red u gridu
     *     gbc.gridy -> kolona u gridu
     * </p>
     *
     */
    public static GridBagConstraints gbc(int x, int y) {
        return gbc(x, y, 1);
    }

    public static GridBagConstraints gbc(int x, int y, double weightX) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = weightX;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;//ako ima prostora širi komponentu horizontalno
        return gbc;
    }
}
