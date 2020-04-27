package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.Project;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ProjectDTO {
    private String projectId;
    private String projectName;
    private String createrId;
    private String creatorName;
    private List<GroupDTO> groupDTOS;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Project convertToProject() {
        ProjectDTOConvert projectDTOConvert = new ProjectDTOConvert();
        return projectDTOConvert.convert(this);
    }

    public ProjectDTO convertFromProject(Project project) {
        ProjectDTOConvert projectDTOConvert = new ProjectDTOConvert();
        return projectDTOConvert.reverse().convert(project);
    }

    public List<GroupDTO> getGroupDTOS() {
        return groupDTOS;
    }

    public void setGroupDTOS(List<GroupDTO> groupDTOS) {
        this.groupDTOS = groupDTOS;
    }

    private static class ProjectDTOConvert extends Converter<ProjectDTO, Project> {

        @Override
        protected Project doForward(ProjectDTO projectDTO) {
            Project project = new Project();
            BeanUtils.copyProperties(projectDTO, project);
            return project;
        }

        @Override
        protected ProjectDTO doBackward(Project project) {
            ProjectDTO dto = new ProjectDTO();
            BeanUtils.copyProperties(project, dto);
            return dto;
        }
    }
}
