package com.releaser.gui.form;

import com.releaser.collector.Collector;
import com.releaser.collector.file.Reader;
import com.releaser.collector.release.Release;
import com.releaser.gui.filter.ReleaseRowFilter;
import com.releaser.gui.model.table.ReleaseTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by droenicke on 28.05.2015.
 */
public class MainForm extends JFrame
{
    private JPanel rootPanel;
    private JTable releasesTable;
    private JTextField folderField;
    private JButton browseFilesButton;
    private JButton collectButton;
    private JLabel filterLabel;
    private JTextField filterField;
    private JTextPane releaseInfoPanel;
    private JButton playButton;
    private JButton scanButton;
    private JTextPane messagePanel;
    private JLabel releaseInfoLabel;
    private JLabel releasesLabel;
    private JPanel infoPanel;

    public MainForm()
    {
        setContentPane(rootPanel);

        //Fit size
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Collect Button onClick
        collectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String path = folderField.getText();
                collectButton.setEnabled(false);
                Collector.collect(path);
                collectButton.setEnabled(true);
            }
        });

        //Scan Button onClick
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String path = folderField.getText();
                Reader reader = new Reader(Paths.get(path));

                try {

                    //Clear Table
                    ReleaseTableModel tableModel = (ReleaseTableModel) releasesTable.getModel();
                    tableModel.getDataVector().removeAllElements();
                    tableModel.fireTableDataChanged();

                    List<Release> releases = reader.read();

                    //Add releases to table
                    for (Release release : releases) {

                        //Only add releases which have releaser files
                        if (release.hasReleaserFile()) {
                            //Fill release with information from releaser file
                            release.hibernateFile();

                            tableModel.addRow(new Object[]{release, release.getModel().omdb.imdbRating});
                        }
                    }

                } catch (IOException|RuntimeException exception) {
                    //Todo Logger
                    messagePanel.setText(exception.getMessage());
                }
            }
        });

        //Release Table onSelect Row
        releasesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event)
            {
                //Prevent invoking listener twice
                if (event.getValueIsAdjusting()) {
                    Release release = (Release) releasesTable.getValueAt(releasesTable.getSelectedRow(), 0);

                    //TODO using HTML Templates
                    releaseInfoPanel.setText(release.getModel().toString());

                    System.out.println(release);
                }
            }
        });

        //Filter Text Field onChange
        filterField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doFilter(filterField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doFilter(filterField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                doFilter(filterField.getText());
            }

            private void doFilter(String filterValue)
            {
                try {
                    TableRowSorter sorter = (TableRowSorter) releasesTable.getRowSorter();

                    sorter.setRowFilter(new ReleaseRowFilter(filterValue));
                } catch (Exception exception) {
                    messagePanel.setText(exception.getMessage());
                }
            }
        });
    }

    private void createUIComponents()
    {
        //Releases Table
        ReleaseTableModel model = new ReleaseTableModel();
        JTable table = new JTable(model);
        TableRowSorter<ReleaseTableModel> sorter = new TableRowSorter<>(model);

        //Table filter
        table.setRowSorter(sorter);

        model.addColumn("Name");
        model.addColumn("Imdb Rating");

        releasesTable = table;

        //Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

        this.infoPanel = infoPanel;

    }
}
