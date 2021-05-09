package fr.plugin.texteditor.ihm;

import fr.serval.application.project.ProjectController;
import fr.serval.controller.ProjectTreeNode;
import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class TextEditorFileTreeView implements IHMComponentBuilder {
    private final TreeView<File> fileTreeView;
    private final TreeItem<File> rootFolder;

    private TextArea textArea;

    public TextEditorFileTreeView() {
        this.fileTreeView = new TreeView<>();
        this.rootFolder = new TreeItem<>();

        this.setupComponent();
    }

    @Override
    public void setupComponent() {
        this.fileTreeView.setRoot(this.rootFolder);
        this.rootFolder.setExpanded(true);

        this.fileTreeView.getSelectionModel().selectedIndexProperty().addListener(e -> {
            File selectedFile = this.fileTreeView.getSelectionModel().getSelectedItem().getValue();
            if (selectedFile != null && selectedFile.isFile()) {
                this.openFile(selectedFile);
            }
        });
    }

    @Override
    public TreeView getComponent() {
        return this.fileTreeView;
    }

    public void updateRootFolder(File rootFolder) {
        this.rootFolder.setValue(rootFolder);
        this.updateFileTree();
    }

    private void updateFileTree() {
        File rootFolder = this.rootFolder.getValue();
        this.rootFolder.getChildren().clear();
        this.fillFileTree(rootFolder, this.rootFolder);
        this.rootFolder.setExpanded(true);
    }

    private void fillFileTree(File root, TreeItem<File> parentItem) {
        for (File child : Objects.requireNonNull(root.listFiles())) {
            TreeItem<File> childItem = new TreeItem<>(child);
            parentItem.getChildren().add(childItem);

            if (child.isDirectory()) {
                fillFileTree(child, childItem);
            }
        }
    }

    public void openFile(File file) {
        this.textArea.setText(getFileContent(file));
    }

    private String getFileContent(File file) {
        StringBuilder fileContent = new StringBuilder();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                fileContent.append(myReader.nextLine()).append('\n');
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
