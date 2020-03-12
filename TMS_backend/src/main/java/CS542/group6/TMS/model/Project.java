package CS542.group6.TMS.model;

import java.util.UUID;

public class Project {

    private UUID projectId;
    private String projectName;

    public Project(UUID projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
