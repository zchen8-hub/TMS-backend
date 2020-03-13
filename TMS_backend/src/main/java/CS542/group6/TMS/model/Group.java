package CS542.group6.TMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "project_group")
public class Group {
    @Id
    @Column(name = "group_id")
    private final UUID groupId;
    @Id
    @Column(name = "project_id")
    private UUID projectId;
    @Column(name = "group_name",nullable = false)
    private String groupName;

    public Group(UUID groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
