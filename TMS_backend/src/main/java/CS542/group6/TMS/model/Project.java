package CS542.group6.TMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "project_id", updatable = false, nullable = false)
    private String projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_id", updatable = false, nullable = false)
    private String createrId;

    @ManyToMany(mappedBy = "projectList")
    private List<User> userList;

    public Project(){}

    public Project(String projectId, String projectName, String createrId, List<User> userList) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.createrId = createrId;
        this.userList = userList;
    }

    public String getProjectId() {
        return projectId;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
