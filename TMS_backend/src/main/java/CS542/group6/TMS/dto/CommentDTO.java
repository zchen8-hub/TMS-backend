package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.Comment;
import CS542.group6.TMS.model.Group;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

public class CommentDTO {

    private String createrId;
    private String comment;
    private String supercid;
    private String transactionId;

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

    /**
     * Convert DTO to PO (Persistence object, completely match parameters of user table )
     * @return PO
     */
    public Comment convertToComment(){
        CommentDTOConvert commentDTOConvert = new CommentDTOConvert();
        return commentDTOConvert.convert(this);
    }

    private static class CommentDTOConvert extends Converter<CommentDTO, Comment> {

        @Override
        protected Comment doForward(CommentDTO commentDTO) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO, comment);
            return comment;
        }

        @Override
        protected CommentDTO doBackward(Comment comment) {
            throw new AssertionError("Reversion is not supported");
        }
    }
}
