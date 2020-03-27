package CS542.group6.TMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

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

    @Column(name = "project_id", updatable = false, nullable = false)
    private String projectId;

    @ManyToMany
    @JoinTable(
            name = "transaction_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transactionList;

    public Tag(String tagId, String tagName, String projectId, List<Transaction> transactionList) {
        this.tagId = tagId;
        this.projectId = projectId;
        this.tagName = tagName;
        transactionList = transactionList;
    }

    public Tag() {
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        transactionList = transactionList;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
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
