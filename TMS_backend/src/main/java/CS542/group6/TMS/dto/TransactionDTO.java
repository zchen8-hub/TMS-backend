package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.Transaction;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TransactionDTO {
    private String transactionId;
    private String groupId;
    private String creatorId;
    private String title;
    private String description;
    private List<UserDTO> userDTOS;

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

    public List<UserDTO> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }

    public Transaction convertToTransaction() {
        TransactionDTOConvert transactionDTOConvert = new TransactionDTOConvert();
        return transactionDTOConvert.convert(this);
    }

    public TransactionDTO convertFromTransaction(Transaction transaction){
        TransactionDTOConvert transactionDTOConvert = new TransactionDTOConvert();
        return transactionDTOConvert.doBackward(transaction);
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
            TransactionDTO dto = new TransactionDTO();
            BeanUtils.copyProperties(transaction, dto);
            return dto;
        }
    }
}
