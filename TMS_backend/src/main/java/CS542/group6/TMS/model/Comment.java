package CS542.group6.TMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "comment_id")
    private UUID commentId;
    @Column(name = "creater_id",nullable = false,updatable = false)
    private UUID createrId;
    @Column(name = "content")
    private String comment;
    @Column(name = "super_cid")
    private UUID supercid;
    @Column(name = "transaction_cid")
    private UUID transactionId;

    public Comment(UUID commentId, UUID createrId, String comment) {
        this.commentId = commentId;
        this.createrId = createrId;
        this.comment = comment;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public UUID getCreaterId() {
        return createrId;
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
