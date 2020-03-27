package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

}
