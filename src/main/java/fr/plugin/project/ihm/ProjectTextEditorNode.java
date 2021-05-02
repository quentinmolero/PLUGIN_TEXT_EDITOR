package fr.plugin.project.ihm;

import fr.plugin.PluginKeys;
import fr.serval.application.project.Project;
import fr.serval.controller.ProjectTreeNode;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

public class ProjectTextEditorNode implements ProjectTreeNode {

    private Project project;
    private TreeItem<String> textEditorNode;

    public ProjectTextEditorNode(Project project) {
        this.project = project;
    }

    @Override
    public void setupComponent() {
        this.textEditorNode = new TreeItem<>(PluginKeys.TEXT_EDITOR_NODE_NAME);
    }

    @Override
    public TreeItem<String> getComponent() {
        return this.textEditorNode;
    }

    @Override
    public Label getDisplayComponent() {
        return new Label("Editeur de texte");
    }
}
