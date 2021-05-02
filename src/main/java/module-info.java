module fr.plugin.texteditor {
    requires fr.serval;
    requires javafx.controls;

    opens fr.plugin to javafx.fxml;

    opens fr.plugin.project.ihm to javafx.controls;
    exports fr.plugin.project.ihm to javafx.controls;

    exports fr.plugin.texteditor;
}