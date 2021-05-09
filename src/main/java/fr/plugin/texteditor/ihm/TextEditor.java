package fr.plugin.texteditor.ihm;

import fr.serval.application.ihm.ApplicationMainView;
import fr.serval.ihm.IHMComponentBuilder;
import fr.serval.tools.GridPaneConstraintBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class TextEditor implements IHMComponentBuilder {
    private final TextEditorFileTreeView textEditorFileTreeView;

    private final GridPane textEditorMainView;
    private final GridPane textEditorFileView;
    private final GridPane textEditorActionView;

    private final Button chooseFolder;
    private final Button saveFile;
    private final TextArea textArea;

    private final DirectoryChooser directoryChooser;

    private File rootDirectory;
    private File openedFile;

    public TextEditor() {
        this.textEditorFileTreeView = new TextEditorFileTreeView(this);

        this.textEditorMainView = new GridPane();
        this.textEditorFileView = new GridPane();
        this.textEditorActionView = new GridPane();

        this.chooseFolder = new Button();
        this.saveFile = new Button();

        this.textArea = new TextArea();

        this.directoryChooser = new DirectoryChooser();

        this.rootDirectory = null;
        this.openedFile = null;

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.chooseFolder.setText("Choisir un repertoire ...");
        this.chooseFolder.setOnAction(event -> {
            this.rootDirectory = directoryChooser.showDialog(ApplicationMainView.getMainStage());
            if (this.rootDirectory != null && this.rootDirectory.exists() && this.rootDirectory.isDirectory()) {
                this.textEditorFileTreeView.updateRootFolder(this.rootDirectory);
            }
        });

        this.saveFile.setText("Sauvegarder");
        this.saveFile.setDisable(true);
        this.saveFile.setOnAction(event -> {
            if (this.openedFile != null && this.openedFile.exists() && this.openedFile.isFile() && this.openedFile.canWrite()) {
                try {
                    Files.writeString(this.openedFile.toPath(), this.textArea.getText());
                    this.saveFile.setDisable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.textArea.setEditable(true);
        this.textArea.setText("Pas de fichier ouvert");
        this.textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            this.saveFile.setDisable(false);
        });

        this.textEditorActionView.add(this.textArea, 0, 0);
        this.textEditorActionView.add(this.saveFile, 0, 1);
        this.textEditorActionView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.textEditorActionView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 90));
        this.textEditorActionView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 10));

        this.textEditorFileView.add(this.textEditorFileTreeView.getComponent(), 0, 0);
        this.textEditorFileView.add(this.textEditorActionView, 1, 0);

        this.textEditorFileView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 30));
        this.textEditorFileView.getColumnConstraints().add(1, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 70));
        this.textEditorFileView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 100));

        this.textEditorMainView.add(this.chooseFolder, 0, 0);
        this.textEditorMainView.add(this.textEditorFileView, 0, 1);

        this.textEditorMainView.getColumnConstraints().add(0, GridPaneConstraintBuilder.buildGridColumnConstraint(Priority.SOMETIMES, 100));
        this.textEditorMainView.getRowConstraints().add(0, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 10));
        this.textEditorMainView.getRowConstraints().add(1, GridPaneConstraintBuilder.buildGridRowConstraint(Priority.SOMETIMES, 90));
    }

    @Override
    public GridPane getComponent() {
        return this.textEditorMainView;
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Button getSaveFile() {
        return saveFile;
    }
}
