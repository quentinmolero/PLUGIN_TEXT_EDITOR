package fr.plugin.project;

import fr.plugin.project.ihm.ProjectTextEditorNode;
import fr.serval.application.project.Project;
import fr.serval.controller.NodeController;
import fr.serval.ihm.IHMComponentBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProjectNodeController implements NodeController {
    private Project project;
    private List<IHMComponentBuilder> pluginNodes;

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public List<IHMComponentBuilder> getNodeList() {
        if (this.pluginNodes == null) {
            this.pluginNodes = new ArrayList<>();
            this.pluginNodes.add(new ProjectTextEditorNode(this.project));
        }
        return this.pluginNodes;
    }
}
