package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.Comment;
import CS542.group6.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment , String> {

    @Query("select c from Comment c where c.transactionId = ?1")
    List<Comment> findALLByTransaction(String transactionId);
}
