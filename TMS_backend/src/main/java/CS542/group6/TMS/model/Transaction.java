package CS542.group6.TMS.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "transaction_proj")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private String transactionId;

    @Column(name = "project_id", updatable = false, nullable = false)
    private String projectId;

    @Column(name = "group_id", updatable = false, nullable = false)
    private String groupId;

    @Column(name = "creator_id", updatable = false, nullable = false)
    private String creatorId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Transaction(){}

    public Transaction(String transactionId, String projectId, String groupId, String creatorId, String title, String description) {
        this.transactionId = transactionId;
        this.projectId = projectId;
        this.groupId = groupId;
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
