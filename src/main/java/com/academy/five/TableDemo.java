package com.academy.five;

import javax.swing.*;

/**
 * Zadaća: Unutar klase {@link PlayerTablePanel2} dodati osim {@link JTable} komponente ispod ili iznad jednu formu
 * za dodavanje novog player-a.
 * <p>
 * Koraci:
 * <li>1. GUI formu sa npr. JPanel koji koristi LayoutManager GridBagLayout</li>
 * <li>2. Iz te forme kada korisnik popuni formu vrijednostima kreirati PlayerInfo objekat sa id = null ali sa svim drugim
 * poljima popunjenim</li>
 * <li>3. Kada kreirate taj objekat/instancu(novog playera) potrebno je da pozovete metodu PlayerInfoDao.save i da insertujete
 * novi record u bazi</li>
 */
//GUI -> JFrame(JTable)  <-> DB
public class TableDemo {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
//        UIManager.setLookAndFeel(new FlatDarkLaf());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(TableDemo::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Deep Table Demonstration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayerTablePanel2 playerTablePanel = new PlayerTablePanel2();
        frame.add(playerTablePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
