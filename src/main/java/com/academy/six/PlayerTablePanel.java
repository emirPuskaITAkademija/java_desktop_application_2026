package com.academy.six;

import com.academy.five.CustomTableColorCellEditor;
import com.academy.five.CustomTableColorCellRenderer;
import com.academy.five.action.ActionCellEditor;
import com.academy.five.action.ActionCellRenderer;
import com.academy.five.action.ActionColumnModel;
import com.academy.four.gui.PlayerInfoTableModel;
import com.academy.seven.PlayerInfoRowFilter;
import com.academy.seven.SearchPlayerDocumentListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import java.awt.*;

// JTable <---- PlayerInfoTableModel(TableModel) ---> podacima PlayerInfoDao
// PlayerFormPanel

/**
 * Najčišće rješenje za SEARCH u Swing je: TableRowSOrter + RowFilter : JTExtField
 */
public class PlayerTablePanel extends JPanel {
    private static final String[] SPORTS = {
            "Football",
            "Basketball",
            "Handball",
            "Volleyball",
            "Tennis",
            "Table Tennis",
            "Badminton",
            "Athletics",
            "Swimming",
            "Water Polo",
            "Boxing",
            "Karate",
            "Taekwondo",
            "Judo",
            "Wrestling",
            "Gymnastics",
            "Cycling",
            "Skiing",
            "Snowboarding",
            "Ice Hockey",
            "Rugby",
            "Cricket",
            "Baseball",
            "Golf",
            "Rowing",
            "Sailing",
            "Climbing",
            "Martial Arts",
            "Esports"
    };
    /**
     * Kako da filtriramo prema searchField tj. našoj @{@link JTextField}
     * <li>1. {@link JTextField} je povezan sa {@link Document}</li>
     * <li>2. {@link Document} povezali sa {@link DocumentListener}</li>
     */
    private final JTextField searchField = new JTextField(25);
    /**
     * On može
     * <li>1. SORTIRATI redove</li>
     * <li>2. FILTRIRATI redove(preko RowFilter)</li>
     * <p>
     * {@link TableRowSorter} priča sa
     * <li> 1. {@link javax.swing.table.TableModel} tj. našim konkretnim table modelom {@link PlayerInfoTableModel}
     * <li>2. {@link RowFilter} tj. naš konkretni {@link PlayerInfoRowFilter}</li>
     */
    private final TableRowSorter<PlayerInfoTableModel> sorter;

    private final PlayerInfoTableModel tableModel;
    private final JTable table;

    public PlayerTablePanel() {
        this.tableModel = new PlayerInfoTableModel();
        this.table = createTable(tableModel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));


        //1) sorter + filter
        this.sorter = new TableRowSorter<>(tableModel); // ono što je u modelu filtrirano prikaže u JTable
        this.table.setRowSorter(sorter);
        add(createSearchBar());

        table.setFillsViewportHeight(true);
        TableColumnModel tableColumnModel = table.getColumnModel();
        TableColumn sportColumn = tableColumnModel.getColumn(3);
        sportColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(SPORTS)));

        add(new JScrollPane(table));
        add(new PlayerFormPanel(tableModel::addNewPlayerInfo));
    }

    private JComponent createSearchBar() {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBorder(new EmptyBorder(0, 0, 10, 0));
        panel.add(new JLabel("Search:"), BorderLayout.WEST);
        panel.add(searchField, BorderLayout.CENTER);
        //filter on typing/unos
        Document document = searchField.getDocument();
        document.addDocumentListener(new SearchPlayerDocumentListener(this::applyFilter));
        return panel;
    }

    private void applyFilter() {
        String text = searchField.getText();
        if (text.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }
        sorter.setRowFilter(new PlayerInfoRowFilter(text));
    }

    private JTable createTable(PlayerInfoTableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.setRowHeight(35);

        table.setDefaultRenderer(Color.class, new CustomTableColorCellRenderer());
        table.setDefaultEditor(Color.class, new CustomTableColorCellEditor());

        table.setDefaultRenderer(ActionColumnModel.class, new ActionCellRenderer());
        table.setDefaultEditor(ActionColumnModel.class, new ActionCellEditor());

        //JTable ---> TableColumnModel --> TableColumn
        TableColumnModel columnModel = table.getColumnModel();
        configureColumnWidths(columnModel, 220, 75, 75, 75, 50, 25, 60);

        return table;
    }

    //JTable ---> TableColumnModel --> TableColumn
    //varargs ---> varijablini broj parametara
    private void configureColumnWidths(TableColumnModel columnModel, int... widths) {
        int min = Math.min(widths.length, columnModel.getColumnCount());
        for (int i = 0; i < min; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(widths[i]);
        }
    }


//    //        onAddPlayerConsumer.accept(playerInfo);
//    private void acceptPlayerInfo(PlayerInfo playerInfo) {
//        tableModel.addNewPlayerInfo(playerInfo);
//    }
}
