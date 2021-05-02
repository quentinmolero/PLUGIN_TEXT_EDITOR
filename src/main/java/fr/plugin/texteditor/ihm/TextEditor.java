package fr.plugin.texteditor.ihm;

import fr.serval.ihm.IHMComponentBuilder;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextEditor implements IHMComponentBuilder {
    private final TextArea textArea;

    public TextEditor() {
        this.textArea = new TextArea();

        this.setupComponent();
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

    @Override
    public void setupComponent() {
        this.textArea.setEditable(true);
        this.textArea.setText(getFileContent(new File(System.getProperty("user.home") + File.separator + "SERVAL" + File.separator + "plugins.lst")));
    }

    @Override
    public TextArea getComponent() {
        return this.textArea;
    }
}
