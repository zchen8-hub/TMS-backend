package CS542.group6.TMS.model;

import CS542.group6.TMS.util.Util;

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
    private final UUID groupId = Util.generate_UUID();

    @Column(name = "project_id", nullable = false)
    private UUID projectId;
    @Column(name = "group_name", nullable = false)
    private String groupName;

    public Group(){}

    public Group(String groupName) {
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
