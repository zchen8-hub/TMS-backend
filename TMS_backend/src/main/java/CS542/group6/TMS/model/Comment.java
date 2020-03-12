package CS542.group6.TMS.model;

import java.util.UUID;

public class Comment {

    private UUID commentid;
    private UUID creater_id;
    private String comment;
    private UUID supercid;

    public Comment(UUID commentid, UUID creater_id, String comment) {
        this.commentid = commentid;
        this.creater_id = creater_id;
        this.comment = comment;
    }

    public UUID getCommentid() {
        return commentid;
    }

    public UUID getCreater_id() {
        return creater_id;
    }

    public UUID getSupercid() {
        return supercid;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSupercid(UUID supercid) {
        this.supercid = supercid;
    }
}
