package com.academy.six;

import com.academy.four.dao.player.PlayerInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import static com.academy.six.GridBagConstraintHelper.gbc;

/**
 * Panel forme kao zasebna UI komponenta(Single Responsibility Principle).
 * <p>
 * Ne zna ništa o tabeli ni DAO-u, samo skuplja input i poziva callback(objasnićemo).
 * <p>
 * JTable <--- TableModel ---> podacima PlayerInfoDao
 * JSpinner <---- SpinnerNumberModel podatke
 * PlayerFormPanel <---- FormSnapshot(PlayerInfo)
 *
 * <p>
 *     Dvije svrhe postojanja:
 *     1. POTPUNO novi PlayerInfo objekat
 *     2. EDITUJE postojeći PlayerInfo
 * </p>
 *
 * <ZADACA>
 * Ispraviti PlayerFormPanel na način da sačuva id od PlayerInfor objekta.
 * </ZADACA>
 */
public class PlayerFormPanel extends JPanel {
    private final JTextField firstNameTextField = new JTextField(12);
    private final JTextField lastNameTextField = new JTextField(12);
    private final JTextField sportTextField = new JTextField(12);
    private final JSpinner yearsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    private final JCheckBox vegetarianCheckBox = new JCheckBox("Vegetarian");
    private final JButton pickColorButton = new JButton("Pick Color");
    private final JButton addPlayerButton = new JButton("Add Player");

    private Color chosenColor = Color.WHITE;
    private final PlayerInfo playerInfo;

    public PlayerFormPanel(Consumer<PlayerInfo> playerInfoConsumer) {
        this(new PlayerInfo(), playerInfoConsumer);
    }

    public PlayerFormPanel(PlayerInfo playerInfo, Consumer<PlayerInfo> playerInfoConsumer) {
        super(new GridBagLayout());
        this.playerInfo = playerInfo;

        setBorder(new EmptyBorder(15, 0, 0, 0));
        pickColorButton.addActionListener(this::onColorPickClick);
        addPlayerButton.setText("Edit Player");
        addPlayerButton.addActionListener(e -> onAddOrEditPlayerButtonClick(playerInfoConsumer));
        initialize();
        buildLayout();
    }

    private void initialize() {
        firstNameTextField.setText(playerInfo.getFirstName());
        lastNameTextField.setText(playerInfo.getLastName());
        sportTextField.setText(playerInfo.getSport());
        SpinnerNumberModel spinnerNumberModel =(SpinnerNumberModel) yearsSpinner.getModel();
        int min = ((Number)spinnerNumberModel.getMinimum()).intValue();
        int max = ((Number)spinnerNumberModel.getMaximum()).intValue();
        Integer years = playerInfo.getYears();
        if(years < min) years = min;
        if(years > max) years = max;
        yearsSpinner.setValue(years);
        vegetarianCheckBox.setSelected(playerInfo.isVegetarian());
    }


    private void buildLayout() {
        int row = 0;
        row(row++, "First Name", firstNameTextField);
        row(row++, "Last Name", lastNameTextField);
        row(row++, "Sport", sportTextField);
        row(row++, "Years", yearsSpinner);
        row(row++, "Vegetarian", vegetarianCheckBox);
        row(row++, "Pick Color", pickColorButton);

        // add buton će zauzeti punu širinu forme
        GridBagConstraints addButtonConstraint = gbc(0, row);
        addButtonConstraint.gridwidth = 2;//zauzmi 2 kolone
        addButtonConstraint.weightx = 1;
        addButtonConstraint.fill = GridBagConstraints.HORIZONTAL;
        add(addPlayerButton, addButtonConstraint);
    }

    private void row(int row, String labelText, JComponent component) {
        add(new JLabel(labelText), gbc(0, row, 0));
        add(component, gbc(1, row, 1));
    }


    private void onColorPickClick(ActionEvent actionEvent) {
        chosenColor = JColorChooser.showDialog(null, "Choose Color", chosenColor);
        if (chosenColor != null) {
            pickColorButton.setBackground(chosenColor);
            pickColorButton.setOpaque(true);
            pickColorButton.setBorderPainted(true);
        }
    }

    //DODAJEMO novi PlayerInfo..korisnik mijenja u GUI kontrolama vrijednosti i mi ih prebacujemo u playerInfo polja
    private void onAddOrEditPlayerButtonClick(Consumer<PlayerInfo> playerInfoConsumer) {
        playerInfo.setFirstName(firstNameTextField.getText());
        playerInfo.setLastName(lastNameTextField.getText());
        playerInfo.setSport(sportTextField.getText());
        playerInfo.setColor(chosenColor);
        playerInfo.setVegetarian(vegetarianCheckBox.isSelected());
        playerInfo.setYears(((Number) yearsSpinner.getValue()).intValue());

        playerInfoConsumer.accept(playerInfo); // -> će tek ovdje pozvati tableModel.addNewPlayerInfo(PlayerInfo playerInfo)

        clearForm();
    }

    private void clearForm() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        sportTextField.setText("");
        yearsSpinner.setValue(0);
        vegetarianCheckBox.setSelected(false);
        chosenColor = Color.WHITE;
        pickColorButton.setBackground(Color.WHITE);
    }

//    private FormSnapshot snapshot() {
//        return new FormSnapshot(
//                firstNameTextField.getText().trim(),
//                lastNameTextField.getText().trim(),
//                sportTextField.getText().trim(),
//                ((Number) yearsSpinner.getValue()).intValue(),
//                vegetarianCheckBox.isSelected(),
//                formModel.color()
//        );
//    }

    /**
     * OOP model(mutabilan ali lokalno i kontrolisano definiran i mutira lokalno).
     * Može se lako pretvoriti u immutable.
     */
//    static final class FormModel {
//        private Color color;
//
//        FormModel(Color color) {
//            this.color = color;
//        }
//
//        Color color() {
//            return color;
//        }
//
//        void setColor(Color color) {
//            this.color = color;
//        }
//    }

    /**
     * FP : immutabilan snapshot forme
     * Pomoću tzv. record-a mogu kreirati immutabilni snapshot stanja forme.
     * <p>
     * Java 14 i 15 -> --enable-preview
     * Java 16 ->
     * <p>
     * record je praktično ista stvar kao i klasa koja želi immutabilne objekte po svom šablonu da se kreiraju.
     */
//    record FormSnapshot(String firstName,
//                        String lastName,
//                        String sport,
//                        int years,
//                        boolean vegetarian,
//                        Color color) {
//
//
//        PlayerInfo toPlayerInfo() {
//            PlayerInfo playerInfo = new PlayerInfo();
//            playerInfo.setFirstName(firstName);
//            playerInfo.setLastName(lastName);
//            playerInfo.setSport(sport);
//            playerInfo.setYears(years);
//            playerInfo.setVegetarian(vegetarian);
//            playerInfo.setColor(color);
//            return playerInfo;
//        }
//    }

}
