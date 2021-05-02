package fr.plugin.texteditor;

import fr.plugin.texteditor.ihm.TextEditorController;
import fr.serval.controller.PluginController;

import java.util.ArrayList;
import java.util.List;

public class PluginTextEditor {
    private final List<PluginController> controllerList;

    public PluginTextEditor() {
        this.controllerList = new ArrayList<>();

        this.controllerList.add(TextEditorController.getInstance());
    }

    public List<PluginController> getControllerList() {
        return controllerList;
    }
}
