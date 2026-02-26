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
 * PlayerFormPanel <---- Model
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

    private final FormModel formModel = new FormModel(Color.WHITE);

    public PlayerFormPanel(Consumer<PlayerInfo> onAddPlayerConsumer) {
        super(new GridBagLayout());
        setBorder(new EmptyBorder(15, 0, 0, 0));
        pickColorButton.addActionListener(this::onColorPickClick);
        addPlayerButton.addActionListener(e -> onAddPlayerClick(onAddPlayerConsumer));
        buildLayout();
    }

    public PlayerFormPanel(PlayerInfo playerInfo, Consumer<PlayerInfo> onEditPlayerConsumer) {
        super(new GridBagLayout());
        setBorder(new EmptyBorder(15, 0, 0, 0));
        pickColorButton.addActionListener(this::onColorPickClick);
        addPlayerButton.setText("Edit Player");
        addPlayerButton.addActionListener(e -> onAddPlayerClick(onEditPlayerConsumer));
        initialize(playerInfo);
        buildLayout();
    }

    private void initialize(PlayerInfo playerInfo) {
        firstNameTextField.setText(playerInfo.getFirstName());
        lastNameTextField.setText(playerInfo.getLastName());
        sportTextField.setText(playerInfo.getSport());
        yearsSpinner.setValue(playerInfo.getYears());
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
        Color chosenColor = JColorChooser.showDialog(null, "Choose Color", formModel.color());
        if (chosenColor != null) {
            formModel.setColor(chosenColor);
            pickColorButton.setBackground(chosenColor);
            pickColorButton.setOpaque(true);
            pickColorButton.setBorderPainted(true);
        }
    }

    private void onAddPlayerClick(Consumer<PlayerInfo> onAddPlayerConsumer) {
        FormSnapshot snapshot = snapshot();
        if (snapshot.firstName().isBlank() || snapshot.lastName().isBlank()) {
            JOptionPane.showMessageDialog(null, "First Name and Last Name cannot be empty");
        }
        PlayerInfo playerInfo = snapshot.toPlayerInfo();
        onAddPlayerConsumer.accept(playerInfo);
        clearForm();
    }

    private void clearForm() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        sportTextField.setText("");
        yearsSpinner.setValue(0);
        vegetarianCheckBox.setSelected(false);
        formModel.setColor(Color.WHITE);
        pickColorButton.setBackground(Color.WHITE);
    }

    private FormSnapshot snapshot() {
        return new FormSnapshot(
                firstNameTextField.getText().trim(),
                lastNameTextField.getText().trim(),
                sportTextField.getText().trim(),
                ((Number) yearsSpinner.getValue()).intValue(),
                vegetarianCheckBox.isSelected(),
                formModel.color()
        );
    }

    /**
     * OOP model(mutabilan ali lokalno i kontrolisano definiran i mutira lokalno).
     * Može se lako pretvoriti u immutable.
     */
    static final class FormModel {
        private Color color;

        FormModel(Color color) {
            this.color = color;
        }

        Color color() {
            return color;
        }

        void setColor(Color color) {
            this.color = color;
        }
    }

    /**
     * FP : immutabilan snapshot forme
     * Pomoću tzv. record-a mogu kreirati immutabilni snapshot stanja forme.
     * <p>
     * Java 14 i 15 -> --enable-preview
     * Java 16 ->
     * <p>
     * record je praktično ista stvar kao i klasa koja želi immutabilne objekte po svom šablonu da se kreiraju.
     */
    record FormSnapshot(String firstName,
                        String lastName,
                        String sport,
                        int years,
                        boolean vegetarian,
                        Color color) {


        PlayerInfo toPlayerInfo() {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setFirstName(firstName);
            playerInfo.setLastName(lastName);
            playerInfo.setSport(sport);
            playerInfo.setYears(years);
            playerInfo.setVegetarian(vegetarian);
            playerInfo.setColor(color);
            return playerInfo;
        }
    }

}
