package CS542.group6.TMS.model;

import java.util.UUID;

public class Group {

    private UUID groupId;
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
