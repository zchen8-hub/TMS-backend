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
import java.util.Optional;

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
        Optional<User> creator = userRepository.findById(transaction.getCreatorId());
        if (creator.isPresent()) {
            transaction.getUserList().add(creator.get());
            creator.get().getTransactionList().add(transaction);
        }
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(String tid, String gid, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.getOne(tid);
        transaction.setGroupId(gid);
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setTitle(transactionDTO.getTitle());
        return transactionRepository.save(transaction);
    }

    public String deleteTransaction(String tid) {
        transactionRepository.deleteById(tid);
        return "success";
    }

    public Transaction addUserToTransaction(String tid, String uid) {
        Optional<Transaction> transaction = transactionRepository.findById(tid);
        User user = userRepository.getOne(uid);
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.getUserList().add(user);
            user.getTransactionList().add(transaction1);
            userRepository.save(user);
            return transaction1;
        }
        return null;
    }

    public Transaction deleteUserFromTransaction(String tid, String uid) {
        Optional<Transaction> transaction = transactionRepository.findById(tid);
        User user = userRepository.getOne(uid);
        if (transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            transaction1.getUserList().remove(user);
            user.getTransactionList().remove(transaction.get());
            userRepository.save(user);
            return transaction1;
        }
        return null;
    }

    public Transaction addTagToTransaction(String tid, String tagId) {
        Optional<Transaction> transaction = transactionRepository.findById(tid);
        Optional<Tag> tag = tagRepository.findById(tagId);
        if (tag.isPresent() && transaction.isPresent()) {
            Transaction transaction1 = transaction.get();
            Tag tag1 = tag.get();
            transaction1.getTagList().add(tag.get());
            tag1.getTransactionList().add(transaction1);
            tagRepository.save(tag1);
            //transactionRepository.save(transaction1);
            return transaction1;
        }
        return null;
    }

    public Transaction deleteTagFromTransaction(String tid, String tagId) {
        Transaction transaction = transactionRepository.getOne(tid);
        Tag tag = tagRepository.getOne(tagId);
        transaction.getTagList().remove(tag);
        return transactionRepository.save(transaction);
    }

    public List<Tag> listTags(String tid) {
        return transactionRepository.findTagsByTransactionId(tid);
    }

    public List<User> listTransactionUsers(String tid) {
        return transactionRepository.findUserByTransactionId(tid);
    }
}
