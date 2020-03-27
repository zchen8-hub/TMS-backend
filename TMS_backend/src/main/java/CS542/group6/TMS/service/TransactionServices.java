package CS542.group6.TMS.service;

import CS542.group6.TMS.dto.TransactionDTO;
import CS542.group6.TMS.model.Group;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.Transaction;
import CS542.group6.TMS.repository.GroupRepository;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {
    private final TransactionRepository transactionRepository;
    private final GroupRepository groupRepository;

    public TransactionServices(TransactionRepository transactionRepository, GroupRepository groupRepository) {
        this.transactionRepository = transactionRepository;
        this.groupRepository = groupRepository;
    }

    public List<Transaction> listTransactions(String gid) {
        Group group = groupRepository.getOne(gid);
        return group.getTransactionList();
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(String tid, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.getOne(tid);
        transaction.setGroupId(transactionDTO.getGroupId());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setTitle(transactionDTO.getTitle());
        return transactionRepository.save(transaction);
    }

    public String deleteTransaction(String tid) {
        transactionRepository.deleteById(tid);
        return "success";
    }
}
