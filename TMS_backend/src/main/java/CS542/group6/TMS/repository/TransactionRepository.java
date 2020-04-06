package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.Transaction;
import CS542.group6.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

    @Query("select t from Tag t join Transaction tr where tr.transactionId = :tid")
    List<Tag> findTagsByTransactionId(@Param("tid") String tid);

    @Query("select u from User u join Transaction tr where tr.transactionId = :tid")
    List<User> findUserByTransactionId(@Param("tid") String tid);
}
