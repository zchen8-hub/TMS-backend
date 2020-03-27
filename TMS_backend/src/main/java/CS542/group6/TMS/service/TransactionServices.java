package CS542.group6.TMS.service;

import CS542.group6.TMS.dto.TransactionDTO;
import CS542.group6.TMS.model.Group;
import CS542.group6.TMS.model.Tag;
import CS542.group6.TMS.model.Transaction;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.GroupRepository;
import CS542.group6.TMS.repository.TagRepository;
import CS542.group6.TMS.repository.TransactionRepository;
import CS542.group6.TMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {
    private final TransactionRepository transactionRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public TransactionServices(TransactionRepository transactionRepository, GroupRepository groupRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
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

    public boolean addUsertoTransaction(String tid, String uid) {
        Transaction transaction = transactionRepository.getOne(tid);
        User user = userRepository.getOne(uid);
        user.getTransactionList().add(transaction);
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserfromTransaction(String tid, String uid) {
        Transaction transaction = transactionRepository.getOne(tid);
        User user = userRepository.getOne(uid);
        user.getTransactionList().remove(transaction);
        userRepository.save(user);
        return true;
    }

    public boolean addTagtoTransaction(String tid, String tagId) {
        Transaction transaction = transactionRepository.getOne(tid);
        Tag tag = tagRepository.getOne(tagId);
        tag.getTransactionList().add(transaction);
        tagRepository.save(tag);
        return true;
    }

    public boolean deleteTagfromTransaction(String tid, String tagId) {
        Transaction transaction = transactionRepository.getOne(tid);
        Tag tag = tagRepository.getOne(tagId);
        tag.getTransactionList().remove(transaction);
        tagRepository.save(tag);
        return true;
    }
}
