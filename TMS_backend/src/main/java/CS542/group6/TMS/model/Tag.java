package CS542.group6.TMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tag")
public class Tag {

    @Column(name = "project_id", updatable = false, nullable = false)
    private String projectId;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tag_id", updatable = false, nullable = false)
    private String tagId;
    @Column(name = "tag_name", nullable = false)
    private String tagName;

    public Tag(String tagId, String tagName,String projectId) {
        this.tagId = tagId;
        this.projectId = projectId;
        this.tagName = tagName;
    }

    public Tag() {
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
