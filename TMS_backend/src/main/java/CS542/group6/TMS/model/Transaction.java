package CS542.group6.TMS.model;

import java.util.UUID;

public class Transaction {

    private UUID transactionId;
    private String title;
    private String description;

    public Transaction(UUID transactionId, String title, String description) {
        this.transactionId = transactionId;
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
