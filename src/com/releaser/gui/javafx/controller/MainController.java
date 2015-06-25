package com.releaser.gui.javafx.controller;

import com.releaser.collector.Collector;
import com.releaser.collector.exception.CollectorException;
import com.releaser.collector.file.Reader;
import com.releaser.collector.release.Release;
import com.releaser.gui.javafx.logging.formatter.ListViewFormatter;
import com.releaser.gui.javafx.logging.handler.ListViewHandler;
import com.releaser.gui.javafx.model.table.ReleaseTableModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Main Controller Class
 *
 * @todo Seperate logic in multiple controller, if possible
 */
public class MainController implements Initializable
{
    @FXML
    private Window mainWindow;

    @FXML
    private TextField folderTextField;

    @FXML
    private Button loadButton;

    @FXML
    private Button scanButton;

    @FXML
    private Button cleanButton;

    @FXML
    private ListView<String> messageListView;

    @FXML
    private TableView<ReleaseTableModel> releaseTable;

    @FXML
    private TableColumn<ReleaseTableModel, String> nameColumn;

    private static final Logger log = Logger.getLogger(MainController.class.getName());

    private final Collector collector = new Collector();

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initListViewLogger();

        //Register onChange event at folderTextField
        folderTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                //Enable Buttons
                loadButton.setDisable(false);
                scanButton.setDisable(false);
                cleanButton.setDisable(false);
            } else {
                //Disable Buttons
                loadButton.setDisable(true);
                scanButton.setDisable(true);
                cleanButton.setDisable(true);
            }
        });

        //Bind ReleaseTableModel Data to Table Columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    /**
     * Opens a directory chooser window
     * Sets the choosed directory path into the folderTextField
     *
     * @param event the event
     */
    @FXML
    protected void openFolder(ActionEvent event)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Choose releases folder");
        File directory = directoryChooser.showDialog(mainWindow);

        if (directory != null) {
            //Set directory path to textfield
            folderTextField.setText(directory.getPath());
        }
    }

    /**
     * Collects information from APIs about each release in given path
     *
     * @param event the event
     */
    @FXML
    protected void doLoad(ActionEvent event)
    {
        String path = folderTextField.getText();
        loadButton.setDisable(true);

        try {
            collector.collect(path);
        } catch (CollectorException e) {
            log.warning(e.getMessage());
        }

        loadButton.setDisable(false);
    }

    /**
     * Collects information from releaser files and displays it in a table
     *
     * @param event the event
     */
    @FXML
    protected void doScan(ActionEvent event)
    {
        String path = folderTextField.getText();
        Reader reader = new Reader(Paths.get(path));

        try {

            //Clear Table
            ObservableList<ReleaseTableModel> tableItems = releaseTable.getItems();
            tableItems.clear();

            List<Release> releases = reader.read();

            //Add releases to table
            for (Release release : releases) {

                //Only add releases which have releaser files
                if (release.hasReleaserFile()) {
                    //Fill release with information from releaser file
                    release.hibernateFile();

                    tableItems.add(new ReleaseTableModel(release.getName()));
                }
            }
        } catch (IOException e) {
            log.warning(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cleans all releaser files in given path
     *
     * @param event the event
     */
    @FXML
    protected void doClean(ActionEvent event)
    {
        String path = folderTextField.getText();
        cleanButton.setDisable(true);

        try {
            collector.clean(path);
            log.info("Cleaned all releaser files in " + path);
        } catch (CollectorException e) {
            log.warning(e.getMessage());
        }

        cleanButton.setDisable(false);
    }

    /**
     * Initialises the Logger for logging into ListViews
     */
    private void initListViewLogger()
    {
        ListViewHandler listViewHandler = new ListViewHandler(messageListView);
        ListViewFormatter listViewFormatter = new ListViewFormatter();

        listViewHandler.setFormatter(listViewFormatter);
        log.addHandler(listViewHandler);

        //Add ListViewHandler to Collector Logger
        Logger collectorLogger = collector.getLogger();
        collectorLogger.addHandler(listViewHandler);
    }
}
