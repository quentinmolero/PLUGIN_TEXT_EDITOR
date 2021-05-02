package fr.plugin.texteditor;

import fr.serval.controller.PluginController;

import java.util.List;

public class Main {
    public static void main() {
        System.out.println("running main from PLUGIN_TEXT_EDITOR");
    }

    public static List<PluginController> getPluginController() {
        PluginTextEditor pluginTextEditor = new PluginTextEditor();
        return pluginTextEditor.getControllerList();
    }
}
