package CS542.group6.TMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "comment_id", nullable = false,updatable = false)
    private String commentId;
    @Column(name = "creater_id", nullable = false,updatable = false)
    private String createrId;
    @Column(name = "content", nullable = false)
    private String comment;
    @Column(name = "super_cid",updatable = false)
    private String supercid;
    @Column(name = "transaction_id", nullable = false,updatable = false)
    private String transactionId;

    public Comment(String commentId, String createrId, String comment, String transactionId) {
        this.commentId = commentId;
        this.createrId = createrId;
        this.comment = comment;
        this.transactionId = transactionId;
    }

    public Comment() {

    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getComment() {
        return comment;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getCreaterId() {
        return createrId;
    }

    public String getSupercid() {
        return supercid;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSupercid(String supercid) {
        this.supercid = supercid;
    }
}
