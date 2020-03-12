package CS542.group6.TMS.model;

import java.util.UUID;

public class Tag {

    private UUID tagId;
    private String tagName;

    public Tag(UUID tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public UUID getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
