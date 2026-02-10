package com.academy.one;

import javax.swing.*;
import java.awt.*;

/**
 * Java Desktop Application Development: GUI
 * <li>1. SWING </li>
 * <li>2. JavaFX</li>
 *
 * <p>
 * Kada kreiramo GUI u programskoj jeziku Java imamo tri grupe komponenti:
 * <li>1. Kontejneri / Container -> skladište kontrole(dugmići, toggle button, check box, radio button, itd) </li>
 * JFrame - prozor
 * JPanel - staviti u prozor
 * <li>2. LayoutManager -> kako će kontrole biti ubačene u kontejner</li>
 * <li>3. GUI controls : JButton, JTextField, JComboBox, JRadioButton, ... sve ono s čim korisnik vaše aplikacije ostvaruje interakciju
 * i kontrolira vašu aplikaciju se zove GUI kontrola.</li>
 * </p>
 */
public class ButtonDemo {
    public static void main(String[] args) {
        //MAIN thread
        // GUI thread da se iscrtava u nekom GUI threadu
        // Thread - dobra praksa da crtanje GUI ili prozora startate u novom thread-u
        Runnable runnable = ButtonDemo::createAndShowGUI;
        SwingUtilities.invokeLater(runnable);
    }

    //1. JFrame => kontejner
    //2. LayoutManager => svaki kontejner ima predefinisani layout manager
    // Predefinisani layout manager koji je povezan sa JFrame je tzv. BorderLayout
    // JFrame <-> BorderLayout
    // GUI controls je u osnovi drugi kontejner koji se zove ButtonPanel
    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        // MAGIC NUMBER 3 mijenjamo radije za int varijablu koja descriptivno opisuje semantiku koja se krije iza broja 3
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Button Demonstration");
        JPanel buttonPanel = new ButtonPanel2();
        frame.setContentPane(buttonPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
