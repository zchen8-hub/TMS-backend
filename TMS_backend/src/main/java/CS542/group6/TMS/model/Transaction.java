package CS542.group6.TMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group_transaction")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "transaction_id", updatable = false, nullable = false)
    private String transactionId;

    @Column(name = "group_id", nullable = false)
    private String groupId;

    @Column(name = "creator_id", updatable = false, nullable = false)
    private String creatorId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "transactionList")
    private List<Tag> tagList;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "transactionList")
    private List<User> userList;

    public Transaction() {
        tagList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public Transaction(String transactionId, String groupId, String creatorId, String title, String description, List<Tag> tagList, List<User> userList) {
        this.transactionId = transactionId;
        this.groupId = groupId;
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
        this.tagList = tagList;
        this.userList = userList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getTransactionId() {
        return transactionId;
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
        return description == null ? "" : description;
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
