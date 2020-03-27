package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.Transaction;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

public class TransactionDTO {
    private String transactionId;
    private String groupId;
    private String creatorId;
    private String title;
    private String description;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction convertToTransaction(){
        TransactionDTOConvert TransactionDTOConvert = new TransactionDTOConvert();
        return TransactionDTOConvert.convert(this);
    }

    private static class TransactionDTOConvert extends Converter<TransactionDTO, Transaction> {

        @Override
        protected Transaction doForward(TransactionDTO transactionDTO) {
            Transaction transaction = new Transaction();
            BeanUtils.copyProperties(transactionDTO, transaction);
            return transaction;
        }

        @Override
        protected TransactionDTO doBackward(Transaction transaction) {
            throw new AssertionError("Reversion is not supported");
        }
    }
}
