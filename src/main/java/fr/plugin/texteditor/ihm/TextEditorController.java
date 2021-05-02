package fr.plugin.texteditor.ihm;

import fr.plugin.project.ProjectNodeController;
import fr.serval.controller.NodeController;
import fr.serval.controller.PluginController;

import java.util.ArrayList;
import java.util.List;

public class TextEditorController implements PluginController {
    private static TextEditorController instance;
    private List<NodeController> nodeControllerList;

    private TextEditorController() {
        System.out.println("TextEditorController was called");
        this.nodeControllerList = new ArrayList<>();
        this.fillNodeControllerList();
    }

    public static TextEditorController getInstance(){
        if (instance == null) {
            instance =  new TextEditorController();
        }

        return instance;
    }

    private void fillNodeControllerList() {
        this.nodeControllerList.add(new ProjectNodeController());
    }

    public List<NodeController> getNodeControllerList() {
        return this.nodeControllerList;
    }
}
